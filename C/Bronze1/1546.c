/**
 * 자기 점수 중에 최댓값을 골라 M
 * 모든 점수를 점수/M*100으로 수정
 * 
 * 첫째 줄에 시험 볼 과목의 개수 N (<=1000)
 * 둘째 줄에 현재 성적 (0 <= C <= 0, 최소 하나는 0 < C)
 * 
 * 첫째 줄에 새로운 평균을 출력한다.
 * 실제 정답과 출력값의 절대오차 또는 상대오차가 10^-2 이하이면 정답
*/

#include <stdio.h>

int main() {
    int M = 0;
    int N;
    float avg = 0;
    int C[1000];

    scanf("%d", &N);
    for(int i = 0; i < N; i++) {
        scanf("%d", &C[i]);
        if(C[i]>M) M = C[i];
    }

    for(int i = 0; i < N; i++) {
        avg += (float)C[i] / M*100;
    }

    printf("%f\n", avg / N);

    return 0;
}