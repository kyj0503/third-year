#include <iostream>
#include <string>
#include <random>    

using namespace std;

class Player {      
	int card[3];    
	string name;    
public:
	Player() :Player("플레이어") { }            
	Player(string name) { this->name = name; }  
	string getName() { return name; }           
	bool playGambling();                        
};
bool Player::playGambling() {
	random_device rd;
	uniform_int_distribution<int> distribution(0, 100);

	for (int i = 0; i < 3; i++) {
		card[i] = distribution(rd) % 3;
		cout << "\t" << card[i];
	}
	for (int i = 0; i < 2; i++) {
		if (card[i] != card[i + 1]) {
			return false;
		}
	}
	return true;
}

class GamblingGame {               
	Player player[2];              
	bool isGameRun = true;  
public:
	GamblingGame();                
	void play() {
		int i = 0;
		while (isGameRun) {
			cout << player[i % 2].getName() << ":<ENTER>";

			char input = cin.get();

			if (input != '\n') {
				cout << "게임이 강제 종료되었습니다!" << endl;
				return;
			}

			if (player[i % 2].playGambling()) {
				isGameRun = false;
				cout << "\t" << player[i % 2].getName() << "님 승리!!" << endl;
			}
			else {
				cout << "\t아쉽군요!" << endl;
			}
			i++;
		}
	}
};

GamblingGame::GamblingGame() {
	cout << "***** 갬블링 게임을 시작합니다. *****" << endl;
	string name;

	while (true) {
		cout << "첫번째 선수 이름>>";
		getline(cin, name);

		if (name.empty()) {
			cout << "이름은 빈 문자열일 수 없습니다. 다시 입력하세요." << endl;
		}
		else {
			player[0] = Player(name);
			break;
		}
	}

	while (true) {
		cout << "두번째 선수 이름>>";
		getline(cin, name);

		if (name.empty()) {
			cout << "이름은 빈 문자열일 수 없습니다. 다시 입력하세요." << endl;
		}
		else {
			player[1] = Player(name);
			break;
		}
	}
}

int main() {
	GamblingGame game;    
	game.play();          

	return 0;
}