#include <iostream>
#include <string>
using namespace std;

class Dept {
    int size;
    int* scores;
public:
    Dept(int size) {
        this->size = size;
        scores = new int[size];
    }
    ~Dept();
    int getSize() { return size; }
    void read();
    bool isOver60(int index);
};

Dept::~Dept() {
    if (scores)
        delete[] scores;

}
int countPass(Dept& dept) {
    int count = 0;
    for (int i = 0; i < dept.getSize(); i++) {
        if (dept.isOver60(i))
            count++;
    }
    return count;
}
void Dept::read() {
    cout << size << "개 점수 입력>> ";
    for (int i = 0; i < size; i++) {
        cin >> scores[i];
    }

}

bool Dept::isOver60(int index) {
    if (scores[index] >= 60)
        return true;
    else
        return false;
}

int main() {
    Dept com(10);
    com.read();
    int n = countPass(com);
    cout << "60점 이상은 " << n << "명";
}
