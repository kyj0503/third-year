#include <iostream>
using namespace std;

char& find(char s[], int index) {
	return s[index]; // s[index] ������ ���� ����
}

int main() {
	char name[] = "Mike";
	cout << name << endl;

	find(name, 0) = 'S'; // find()�� ������ ��ġ�� ���� 'm' ����.
						 // name[0]='S'�� ����
	cout << name << endl;

	char& ref = find(name, 2); // ref�� name[2] ����
	ref = 't'; // name = "Site"
	cout << name << endl;
}