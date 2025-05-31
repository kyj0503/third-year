/** 실습과제-4 Unix 파이프 과제
 * 
 * myshell.c 요구사항
 * (1) 명령어 순차적 실행 기능 ([shell] cmd1; cmd2; cmd3)
 * (2) 입출력 재지정 기능 ([shell] cmd > outfile, [shell] cmd < infile)
 * (3) 명령어 후면(background) 실행 기능 ([shell] cmd &)
 * 
 * mypipe.c 요구사항
 * [shell] cmd1 | cmd2
 * (1) 두 개의 자식 프로세스를 생성하여 cmd1은 첫 번째 자식 프로세스가 실행(exec)하고 cmd2는 두 번째 자식 프로세스가 실행하도록 합니다.
 *     부모 프로세스는 자식 프로세스가 모두 끝나기를 기다립니다.
 * (2) cmd1의 표준출력이 파이프를 통해 cmd2의 표준입력이 되어야 합니다.
 * (3) cmd1과 cmd2는 각각 명령줄 인수를 포함할 수 있도록 처리해야 합니다.
 * 
 * @author 소프트웨어 202284011 김연재
*/

#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/wait.h>

#define MAXARG 7    // 최대 명령줄 인수 개수

void execute(char *cmd);    // 단일 명령어를 실행하는 함수
void execute_pipe(char *cmd1, char *cmd2);    // 파이프 명령어를 실행하는 함수

int main() {
    char str[256];    // 사용자 입력을 저장할 버퍼
    char *args[MAXARG];    // 인수를 저장할 배열
    char *s;
    char *save;
    int argn;

    while (1) {    // 무한 루프로 사용자 입력을 받음
        printf("[shell] ");
        if (fgets(str, sizeof(str), stdin) == NULL) break;    // 사용자 입력을 받음
        
        // 명령어 파이프 기능 ([shell] cmd1 | cmd2)
        char *cmd1 = strtok_r(str, "|", &save);    // 입력을 '|'로 분리
        char *cmd2 = strtok_r(NULL, "|", &save);    // 다음 '|'로 분리된 문자열을 가져옴

        if (cmd2 != NULL) {    // 파이프가 있는 경우
            execute_pipe(cmd1, cmd2);
        } else {    // 파이프가 없는 경우
            // 명령어 순차적 실행 기능 ([shell] cmd1; cmd2; cmd3)
            char *cmd = strtok_r(str, ";", &save);

            while (cmd != NULL) {
                execute(cmd);
                cmd = strtok_r(NULL, ";", &save);
            }
        }
    }
    return 0;
}

void execute(char *cmd) {
    char *args[MAXARG];    // 인수를 저장할 배열
    char *s;
    char *save;
    int argn = 0;
    static const char delim[] = " \t\n";    // 구분자를 정의
    int pid, status;
    int bg = 0;    // 백그라운드 실행 여부를 저장
    int fd;

    // 백그라운드 실행 기능을 처리
    if (strchr(cmd, '&')) {
        bg = 1;
        *strchr(cmd, '&') = '\0';
    }

    // cmd를 delim에 정의된 구준자를 기준으로 분리하고, 분리된 각 부분을 args 배열에 저장한다.
    // while로 문자열을 더 이상 분리할 수 없을 때까지 반복한다.
    // 분리된 문자열을 args 배열에 저장하고, 인수의 개수를 나타내는 argn을 1 증가시킨다.
    // strtok_r 함수를 다시 호출하여 문자열을 계속 분리한다. 첫 번째 인수를 NULL로 주어 이전에 분리했던 문자열에서 이어서 분리하도록 한다.
    s = strtok_r(cmd, delim, &save);
    while (s) {    
        args[argn++] = s;    
        s = strtok_r(NULL, delim, &save);    
    }

    args[argn] = (char *)0;

    if (argn == 0) return;    // 인수가 없는 경우 함수를 종료
   
    // 입출력 재지정 기능을 처리 (리다이렉션은 표준 입력, 표준 출력, 또는 표준 에러를 다른 위치로 변경하는 기능)
    int i;
    for (i = 0; i < argn; ++i) {

        // 출력 재지정 - > 기호 뒤에 오는 파일로 표준 출력을 리다이렉션한다.
        // open 함수를 사용하여 파일을 쓰기 모드로 열고, 파일 디스크립터를 STDOUT_FILENO로 복사(dup2)하여 표준 출력을 해당 파일로 변경한다.
        // 그 후 close 함수를 사용하여 원래의 파일 디스크립터를 닫는다.
        if (strcmp(args[i], ">") == 0) {    
            fd = open(args[i + 1], O_WRONLY | O_CREAT | O_TRUNC, 0644);
            if (fd < 0) {
                perror("open");
                return;
            }
            dup2(fd, STDOUT_FILENO);
            close(fd);
            args[i] = NULL;
            break;
        }

        // 입력 재지정 - < 기호 뒤에 오는 파일로 표준 입력을 리다이렉션한다.
        // open 함수를 사용하여 파일을 읽기 모드로 열고, 파일 디스크립터를 STDIN_FILENO로 복사(dup2)하여 표준 입력을 해당 파일로 변경한다.
        // 그 후 close 함수를 사용하여 원래의 파일 디스크립터를 닫는다.
        else if (strcmp(args[i], "<") == 0) {    
            fd = open(args[i + 1], O_RDONLY);
            if (fd < 0) {
                perror("open");
                return;
            }
            dup2(fd, STDIN_FILENO);
            close(fd);
            args[i] = NULL;
            break;
        }
    }

    // quit 명령어를 처리
    if (!strcmp(args[0], "quit"))
        exit(0);

    // 자식 프로세스를 생성하여 명령어를 실행
    if ((pid = fork()) == -1) {
        perror("fork failed");
    } else if (pid != 0) {
        if (!bg) {    // 백그라운드 실행이 아닌 경우 자식 프로세스가 종료될 때까지 기다린다.
            if (waitpid(pid, &status, 0) == -1) {
                perror("waitpid failed");
            }
        }
    } else {
        execvp(args[0], args);
        perror("execvp failed");
        exit(1);
    }
}

void execute_pipe(char *cmd1, char *cmd2) {
    int pipefd[2];    // 파이프 파일 디스크립터를 저장할 배열
    pid_t pid1, pid2;

    // 파이프를 생성
    if (pipe(pipefd) == -1) {
        perror("pipe failed");
        return;
    }

    // 첫 번째 자식 프로세스를 생성하여 cmd1을 실행
    if ((pid1 = fork()) == -1) {
        perror("fork failed");
        return;
    }

    if (pid1 == 0) {
        // 첫 번째 자식 프로세스 - cmd1
        close(pipefd[0]); // 파이프 읽기 닫기
        dup2(pipefd[1], STDOUT_FILENO); // 표준 출력을 파이프로 연결
        close(pipefd[1]);

        execute(cmd1);
        exit(1);
    }

    // 두 번째 자식 프로세스를 생성하여 cmd2를 실행
    if ((pid2 = fork()) == -1) {
        perror("fork failed");
        return;
    }

    if (pid2 == 0) {
        // 두 번째 자식 프로세스 - cmd2
        close(pipefd[1]); // 파이프 쓰기 닫기
        dup2(pipefd[0], STDIN_FILENO); // 표준 입력을 파이프로 연결
        close(pipefd[0]);

        execute(cmd2);
        exit(1);
    }

    close(pipefd[0]);
    close(pipefd[1]);

    // 부모 프로세스는 두 자식 프로세스가 종료될 때까지 기다림
    waitpid(pid1, NULL, 0);
    waitpid(pid2, NULL, 0);
}
