#include <iostream> 
using namespace std;

class Circle {              // Circle 선언부
public:
	int radius;
	double getArea();
};

double Circle::getArea() {  // Circle 구현부
	return 3.14 * radius * radius;
}

int main() {
	Circle donut;           // 객체 dount 생성
	donut.radius = 1;       // donut의 멤버 변수 접근
	double area = donut.getArea(); // donut의 멤버 함수 호출
	cout << "donut 면적은 " << area << endl;

	Circle pizza;
	pizza.radius = 30; // pizza 객체의 반지름을 30으로 설정
	area = pizza.getArea(); // pizza 객체의 면적 알아내기
	cout << "pizza 면적은 " << area << endl;
}
