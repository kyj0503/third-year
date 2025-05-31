/*
함수 중복 연습
큰 수를 리턴하는 다음 두 개의 big 함수를 중복 구현하라.
int big(int a, int b);      //a와 b 중 큰 수 리턴
int big(int a[], int size); // 배멸 a[]에서 가장 큰 수 리턴
*/

#include <iostream>
using namespace std;

int big(int a, int b) {
	if (a > b) return a;
	else return b;
}

int big(int a[], int size) {
	int res

}