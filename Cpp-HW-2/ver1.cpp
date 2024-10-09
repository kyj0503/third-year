#include <iostream>
#include <cstdlib>   // rand(), srand()
#include <ctime>     // time()
#include <string>

using namespace std;

// Player 클래스 정의
class Player {
    string name;
public:
    Player(string name) : name(name) {}
    string getName() { return name; }
    bool turn() {
        cout << name << "의 차례입니다. <Enter> 키를 입력하세요.";
        cin.ignore(); // Enter 키 입력 대기
        int numbers[3];
        // 3개의 랜덤 숫자 생성 (0 ~ 2)
        for (int i = 0; i < 3; i++) {
            numbers[i] = rand() % 3;
        }
        // 생성된 숫자 출력
        cout << numbers[0] << "\t" << numbers[1] << "\t" << numbers[2] << endl;
        // 3개의 숫자가 모두 같은지 확인
        if (numbers[0] == numbers[1] && numbers[1] == numbers[2]) {
            cout << name << "님이 승리하였습니다!!" << endl;
            return true;  // 승리 시 true 반환
        }
        else {
            cout << "아쉽군요!" << endl;
            return false; // 승리 실패 시 false 반환
        }
    }
};

// GamblingGame 클래스 정의
class GamblingGame {
    Player* player1;
    Player* player2;
public:
    GamblingGame(string name1, string name2) {
        player1 = new Player(name1);
        player2 = new Player(name2);
    }
    ~GamblingGame() {
        delete player1;
        delete player2;
    }
    void start() {
        cout << "***** 갬블링 게임을 시작합니다. *****" << endl;
        while (true) {
            if (player1->turn()) break;  // player1의 차례
            if (player2->turn()) break;  // player2의 차례
        }
    }
};

int main() {
    srand((unsigned)time(0)); // 랜덤 시드 설정

    string name1, name2;
    cout << "첫번째 선수 이름>>";
    getline(cin, name1);
    cout << "두번째 선수 이름>>";
    getline(cin, name2);

    // 갬블링 게임 시작
    GamblingGame game(name1, name2);
    game.start();

    return 0;
}
