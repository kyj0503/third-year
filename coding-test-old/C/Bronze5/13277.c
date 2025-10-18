#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int charToInt(char c) {
    return (int)(c - '0'); 
}

char intToChar(int num) {
    return (char)(num + '0');
}

char* multiply(char* num1, char* num2) {
    int len1 = strlen(num1);
    int len2 = strlen(num2);
    int lenResult = len1 + len2;
    
    int *result = (int*)calloc(lenResult, sizeof(int));
    
    for (int i = len1 - 1; i >= 0; i--) {
        for (int j = len2 - 1; j >= 0; j--) {
            int mul = charToInt(num1[i]) * charToInt(num2[j]);
            int sum = mul + result[i + j + 1];
            
            result[i + j + 1] = sum % 10;
            result[i + j] += sum / 10;
        }
    }
    
    char *resultStr = (char*)malloc((lenResult + 1) * sizeof(char));
    int index = 0;
    int start = 0;
    while (start < lenResult && result[start] == 0) start++;
    if (start == lenResult) {
        resultStr[index++] = '0';
    } else {
        for (int i = start; i < lenResult; i++) {
            resultStr[index++] = intToChar(result[i]);
        }
    }
    resultStr[index] = '\0';
    
    free(result);
    
    return resultStr;
}

int main() {
    char num1[1000], num2[1000];

    scanf("%s %s", num1, num2);
    
    char *result = multiply(num1, num2);
    
    printf("%s\n", result);
    
    free(result);
    
    return 0;
}