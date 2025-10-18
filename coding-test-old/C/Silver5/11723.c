#include <stdio.h>
#include <string.h>

int S[21] = {0}; // 집합 S를 나타내는 배열, 인덱스 1부터 사용

// add 연산 함수
void add(int x) {
    if (S[x] == 0) // 이미 존재하는 경우 무시
        S[x] = 1;
}

// remove 연산 함수
void remove_element(int x) {
    if (S[x] == 1) // 존재하는 경우에만 제거
        S[x] = 0;
}

// check 연산 함수
void check(int x) {
    printf("%d\n", S[x]); // S[x]의 값을 출력
}

// toggle 연산 함수
void toggle(int x) {
    S[x] = 1 - S[x]; // 존재하면 제거, 없으면 추가
}

// all 연산 함수
void all_elements() {
    for (int i = 1; i <= 20; i++)
        S[i] = 1; // 모두 추가
}

// empty 연산 함수
void empty_set() {
    for (int i = 1; i <= 20; i++)
        S[i] = 0; // 모두 제거
}

int main() {
    int M; // 연산의 수
    scanf("%d", &M);

    char op[7]; // 연산 종류를 나타내는 문자열
    int x; // 추가나 제거할 숫자

    for (int i = 0; i < M; i++) {
        scanf("%s %d", op, &x);

        if (strcmp(op, "add") == 0) {
            add(x);
        } else if (strcmp(op, "remove") == 0) {
            remove_element(x);
        } else if (strcmp(op, "check") == 0) {
            check(x);
        } else if (strcmp(op, "toggle") == 0) {
            toggle(x);
        } else if (strcmp(op, "empty") == 0) {
            empty_set();
        } else if (strcmp(op, "all") == 0) {
            all_elements();
        } else {
            printf("잘못된 연산\n");
        }
    }

    return 0;
}
