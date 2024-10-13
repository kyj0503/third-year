#include <iostream>
using namespace std;

char& find(char s[], int index) {
	return s[index]; // s[index] 공간의 참조 리턴
}

int main() {
	char name[] = "Mike";
	cout << name << endl;

	find(name, 0) = 'S'; // find()가 리턴한 위치에 문자 'm' 저장.
						 // name[0]='S'로 변경
	cout << name << endl;

	char& ref = find(name, 2); // ref는 name[2] 참조
	ref = 't'; // name = "Site"
	cout << name << endl;
}