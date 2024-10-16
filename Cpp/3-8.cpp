#include <iostream> 
using namespace std;

class Circle {
public:
	int radius;
	Circle();
	Circle(int r);
	~Circle();
	double getArea();
};

Circle::Circle() {
	radius = 1;
	cout << "馆瘤抚 " << radius << " 盔 积己" << endl;
}

Circle::Circle(int r) {
	radius = r;
	cout << "馆瘤抚 " << radius << " 盔 积己" << endl;
}

Circle::~Circle() {
	cout << "馆瘤抚 " << radius << " 盔 家戈" << endl;
}

double Circle::getArea() {
	return 3.14 * radius * radius;
}

Circle globalDonut(1000); // 傈开 按眉 积己
Circle globalPizza(2000);

void f() {
	Circle fDonut(100);   // 瘤开 按眉 积己
	Circle fPizza(200);
}

int main() {
	Circle mainDonut;     // 瘤开 按眉 积己  
	Circle mainPizza(30);
	f();
}
