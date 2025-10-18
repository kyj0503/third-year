/** 이상한 기호
 * 연산자 기호는 @
 * A@B = (A+B)*(A-B)
*/

#include <stdio.h>

int main() {
    long long a, b;

    scanf("%lld %lld", &a, &b);

    printf("%lld", (a+b)*(a-b));
    
    return 0;
}