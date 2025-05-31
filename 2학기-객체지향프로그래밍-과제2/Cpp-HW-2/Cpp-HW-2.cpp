#include <iostream>
#include <string>
#include <random>    

using namespace std;

class Player {      
	int card[3];    
	string name;    
public:
	Player() :Player("�÷��̾�") { }            
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
				cout << "������ ���� ����Ǿ����ϴ�!" << endl;
				return;
			}

			if (player[i % 2].playGambling()) {
				isGameRun = false;
				cout << "\t" << player[i % 2].getName() << "�� �¸�!!" << endl;
			}
			else {
				cout << "\t�ƽ�����!" << endl;
			}
			i++;
		}
	}
};

GamblingGame::GamblingGame() {
	cout << "***** ���� ������ �����մϴ�. *****" << endl;
	string name;

	while (true) {
		cout << "ù��° ���� �̸�>>";
		getline(cin, name);

		if (name.empty()) {
			cout << "�̸��� �� ���ڿ��� �� �����ϴ�. �ٽ� �Է��ϼ���." << endl;
		}
		else {
			player[0] = Player(name);
			break;
		}
	}

	while (true) {
		cout << "�ι�° ���� �̸�>>";
		getline(cin, name);

		if (name.empty()) {
			cout << "�̸��� �� ���ڿ��� �� �����ϴ�. �ٽ� �Է��ϼ���." << endl;
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

