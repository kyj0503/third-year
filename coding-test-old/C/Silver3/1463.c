#include <stdio.h>
#define min(a, b) ((a) < (b) ? (a) : (b))

int main() {
    int X;
    
    scanf("%d", &X);

    int cnt[X+1];
    
	cnt[1] = 0;
    cnt[2] = 1;
    cnt[3] = 1;

    for (int i = 4; i <= X; i++) {
    
	    cnt[i] = cnt[i-1] + 1;
    
	    if (i % 3 == 0) {
    
	        cnt[i] = min(cnt[i], cnt[i/3] + 1);
    
	    }
    
	    if (i % 2 == 0) {
    
	        cnt[i] = min(cnt[i], cnt[i/2] + 1);
        }
    }

    printf("%d", cnt[X]);

    return 0;
}
