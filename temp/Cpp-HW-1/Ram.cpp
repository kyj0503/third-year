#include "Ram.h"
#include <iostream>
using namespace std;

Ram::Ram() {
    size = 100 * 1024;
    for (int i = 0; i < size; i++) {
        mem[i] = 0;
    }
}

Ram::~Ram() {
    cout << "皋葛府 力芭凳" << endl;
}

char Ram::read(int address) {
    if (address >= 0 && address < size) {
        return mem[address];
    }
    else {
        cout << "肋给等 林家 立辟" << endl;
        return 0;
    }
}

void Ram::write(int address, char value) {
    if (address >= 0 && address < size) {
        mem[address] = value;
    }
    else {
        cout << "肋给等 林家 立辟" << endl;
    }
}