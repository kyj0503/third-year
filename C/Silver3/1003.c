/** 피보나치 함수
 * fibonacci(n)을 호출했을 때, 0과 1이 각각 몇번 출려되는지 구하는 프로그램을 작성하시오.
*/

#include <stdio.h>

int cnt0 = 0, cnt1 = 0;

int fibonacci(int n) {
    if (n == 0) {
        cnt0++; 
        return 0;
    } else if (n == 1) {
        cnt1++;
        return 1;
    } else {
        return fibonacci(n-1) + fibonacci(n-2);
    }
}

int main() {

    int num;

    scanf("%d", &num);

    int input[num+1];

    for(int i = 0; i < num; i++){
        scanf("%d", &input[i]);
    }

    for(int j = 0; j < num; j++){
        fibonacci(input[j]);
        printf("%d %d\n", cnt0, cnt1);
        cnt0 = 0, cnt1 = 0;
    }

    return 0;
}
