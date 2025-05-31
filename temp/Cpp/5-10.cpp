// 얕은 복사 생성자를 사용하여 프로그램이 비정상 종료되는 경우

#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
using namespace std;

class Person {                             // Person 클래스 선언
	char* name;
	int id;
public:
	Person(int id, const char* name);      // 생성자
	~Person();                             // 소멸자
	void changeName(const char* name);
	void show() { cout << id << ',' << name << endl; }
};
Person::Person(int id, const char* name) { // 생성자
	this->id = id;
	int len = strlen(name);                // name의 문자 개수
	this->name = new char[len + 1];        // name 문자열 공간 할당. heap 메모리 영역
	strcpy(this->name, name);              // name에 문자열 복사
}
Person::~Person() {                        // 소멸자
	if (name)                              // 만일 name에 동적 할당된 배열이 있으면
		delete[] name;                     // 동적 할당 메모리 소멸. name 메모리 반환
}
void Person::changeName(const char* name) {// 이름 변경
	if (strlen(name) > strlen(this->name))
		return;
	strcpy(this->name, name);
}

int main() {
	Person father(1, "Kitae");    // (1) father 객체 생성
	Person daughter(father);      // (2) daughter 객체 복사 생성. 컴파일러가 삽입한 디폴트 복사 생성자 호출

	cout << "daughter 객체 생성 직후 ----" << endl;
	father.show();                // (3) father 객체 출력
	daughter.show();              // (3) daughter 객체 출력

	daughter.changeName("Grace"); // (4) daughter의 이름을 "Grace"로 변경
	                              // 얕은 복사는 같은 힙 공간을 공유 -> father의 이름도 "Grace"로 변경됨.
	cout << "daughter 이름을 Grace로 변경한 후 ----" << endl;
	father.show();                // (5) father 객체 출력
	daughter.show();              // (5) daughter 객체 출력

	return 0;                     // (6), (7) daughter, father 객체 소멸
	                              // daughter, father 순으로 소멸. father가 소멸할 때, 프로그램 비정상 종료됨
}


