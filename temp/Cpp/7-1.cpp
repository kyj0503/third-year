#include <iostream>
using namespace std;

class Rect;
bool equals(Rect r, Rect s); //equals() �Լ� ����

class Rect { //Rect Ŭ���� ����
	int width, height;
public:
	Rect(int width, int height) { this->width = width; this->height = height; }
	friend bool equals(Rect r, Rect s);
};

