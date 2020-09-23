#include <iostream>
#include <string>
using namespace std;

int main() {
	//string str1 = "9-8P72S9P-9S-P9-98";
	//string str1 = "9PS9P9P8-7P72733PSSS";
	//string str1 = "7P7PSS729P9P639P9P9";
	string str1;
	cin >> str1;
	//string str1 = "7-549P7P25S54SS7PS";
	//string str1 = "-5---59-S9-8-81818P8";
	//string str1 = "72-P-981368161518--9";
	//string str1 = "8P9-9PS71S63S63S8-";
	//string str1 = "S9P7-9PSS8PS9P9P7";
	//string str1 = "---P--9-9-7---S8--3";
	//string str1 = "S6P9-637PS617-318-";
	//string str1 = "SSSSSSSSSSSS"; 


	int score[11] = { 0, };


	int index = 0;
	int frame = 0;
	int flag = 0;
	int temp = 0;
	for (int i = 0; i < str1.size() || frame == 11; ++i) {
		//cout << str1[i];
		char ch;


		switch (str1[i]) {
		case '-':
			break;
		case 'P':
			ch = str1[i - 1];
			if (ch == '-') {
				temp += 10;
			}
			else {
				temp += 10 - (ch - '0');
			}//이 전까지 체크
			
			if (i + 1 < str1.size()) {
				ch = str1[i + 1];

				if (ch == 'S') {
					temp += 10;
				}
				else if (ch == '-') {
					
				}
				else {
					temp += ch - '0';
				}
			}
			break;
		case 'S':
			flag += 1;
			temp += 10;
			if (i + 2 >= str1.size()) break;
			for (int k = 1; k < 3; ++k) {
				if (str1[i + k] == 'S') temp += 10;
				else if (str1[i + k] == 'P') {
					if (str1[i + k - 1] == '-') temp += 10;
					else temp += 10 - (str1[i + k - 1] - '0');
				}
				else if (str1[i + k] == '-') {

				}
				else temp += str1[i + k] - '0';
			}
			break;
		default:
			ch = str1[i];
			temp += ch - '0';
			break;
		}
		flag += 1;
		if(flag >= 2) {
			score[frame++] = temp;
			flag = 0;
			
			temp = 0;
			
			if (frame == 10) {
				index = i;
			}
			//cout << "|";
		}

	}



	//cout << '\n';

	/*
	printf("%s", "Frame");
	for (int i = 1; i <= 10; ++i) {
		if (i == 10) {
			printf("       %d", i);
			continue;
		}
		printf("%6d", i);
	}
	printf("   %s\n", "ToTal");

	printf("===========================================================================================\n");

	printf("%s", "Score");
	frame = 0;
	flag = 0;
	for (int i = 0; i < size(str1); ++i) {
		if (i >= index) {
			printf("%3c", str1[i]);
			continue;
		}
		printf("%3c", str1[i]);
		if (str1[i] == 'S') {
			printf("   ");
		}
		
	}

	printf("\n==============================================================================\n");
	printf("%s", "Sum  ");*/
	int z = 0;
	for (int i = 1; i <= 10; ++i) {
		z += score[i - 1];
		/*if (i == 10) {
			printf("       %d", score[i-1]);
			continue;
		}
		printf("%6d", score[i-1]);*/
	}
	
	printf("%d\n", z);

	

	return 0;
}
