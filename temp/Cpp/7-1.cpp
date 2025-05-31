#include <iostream>
using namespace std;

class Rect;
bool equals(Rect r, Rect s); //equals() 함수 선언

class Rect { //Rect 클래스 선언
	int width, height;
public:
	Rect(int width, int height) { this->width = width; this->height = height; }
	friend bool equals(Rect r, Rect s);
};

