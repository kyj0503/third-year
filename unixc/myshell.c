/** 실습과제-3 Unix 서버 프로세스 과제
 * 
 * '~/09/shell.c' file을 참조하여 다음의 기능을 가진 셸 인터프리터(shell interpreter) program "myshell.c"를 작성
 * (다음에서 "[shell] "은 셸 인터프리터의 프롬프트(prompt)입니다.)
 * - 다음 -
 * (1) 명령어 순차적 실행 기능 ([shell] cmd1; cmd2; cmd3)
 * (2) 입출력 재지정 기능 ([shell] cmd > outfile, [shell] cmd < infile)
 * (3) 명령어 후면(background) 실행 기능 ([shell] cmd &)
 * 
 * @author 소프트웨어 202284011 김연재
*/

#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>

#define MAXARG 7

void execute(char *cmd);

int main() {
    char buf[256];
    char *args[MAXARG];
    char *s;
    char *save;
    int argn;
    static const char delim[] = " \t\n";          /* 공백, 탭, 개행으로 이루어진 구분자 선언 */
    int pid, status;

    while (1) {                                   /* 무한 반복 */
        printf("[shell] ");                       /* 프롬프트 출력 */
        if (fgets(buf, sizeof(buf), stdin) == NULL) break;    
        
        // (1) 명령어 순차적 실행 기능 ([shell] cmd1; cmd2; cmd3)
        char *cmd = strtok_r(buf, ";", &save);    /* 세미콜론으로 명령어 분리 */

        while (cmd != NULL) {
            execute(cmd);                         /* 분리된 각 명령어 실행 */
            cmd = strtok_r(NULL, ";", &save);
        }
    }
    return 0;
}

void execute(char *cmd) {
    char *args[MAXARG];
    char *s;
    char *save;
    int argn = 0;
    static const char delim[] = " \t\n"; /* 공백, 탭, 개행으로 이루어진 구분자 선언 */
    int pid, status;
    int bg = 0;                          /* 백그라운드 실행 여부 플래그 */
    int fd;

    // (3) 명령어 후면(background) 실행 기능 ([shell] cmd &)
    if (strchr(cmd, '&')) {
        bg = 1;
        *strchr(cmd, '&') = '\0';        /* '&'를 명령어에서 제거 */
    }

    s = strtok_r(cmd, delim, &save);     /* 문자열에서 delim을 기준으로 단어를 잘라냄 */
    while (s) {
        args[argn++] = s;
        s = strtok_r(NULL, delim, &save);
    }

    args[argn] = (char *)0;              /* 인수가 더 없음을 의미하는 문자 추가 */

    if (argn == 0) return;               /* 명령어가 없으면 리턴 */

    // (2) 입출력 재지정 기능 ([shell] cmd > outfile, [shell] cmd < infile)
    int i;
    for (i = 0; i < argn; ++i) {
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
        } else if (strcmp(args[i], "<") == 0) {
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

    if (!strcmp(args[0], "quit"))        /* 프롬프트로 입력받은 단어가 'quit'이면 while 문 벗어남 */
        exit(0);

    if ((pid = fork()) == -1) {
        perror("fork failed");
    } else if (pid != 0) {
        if (!bg) {                       /* 백그라운드 실행이 아니면 부모 프로세스는 자식 프로세스가 종료되기를 기다림 */
            if (waitpid(pid, &status, 0) == -1) {
                perror("waitpid failed");
            }
        }
    } else {
        execvp(args[0], args);           /* 자식 프로세스는 execvp를 이용하여 args[0] 실행 */
        perror("execvp failed");
        exit(1);
    }
}
