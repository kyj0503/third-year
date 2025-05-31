/*
디폴트 매개 변수를 가진 함수 만들기 연습
f(); // 한 줄에 빈칸 10개 출력한다.
f('%'); // 한 줄에 '%'를 10개 출력한다.
f('@', 5); // 다섯 줄에 '@'를 10개 출력한다.
*/

#include <iostream>
using namespace std;

void f(char c = ' ', int line = 1);

void f(char c, int line) {
	for (int i = 0; i < line; i++) {
		for (int j = 0; j < 10; j++) {
			cout << c;
		}
		cout << endl;
	}
}

int main() {
	f();
	f('%');
	f('@', 5);
}