/*
����Ʈ �Ű� ������ ���� �Լ� ����� ����
f(); // �� �ٿ� ��ĭ 10�� ����Ѵ�.
f('%'); // �� �ٿ� '%'�� 10�� ����Ѵ�.
f('@', 5); // �ټ� �ٿ� '@'�� 10�� ����Ѵ�.
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