#include <iostream>
#include <string>

using namespace std;

int main() {
	int T;
	string s;
	int fir, sec;
	cin >> T;
	for (int i = 0; i < T; ++i) {
		cin >> s;

		fir = stoi(s.substr(0, 2));
		sec = stoi(s.substr(2, 4));
		if (fir > 12 || fir == 0) {
			if (sec > 12 || sec == 0)
				cout << '#' << i + 1 << ' ' << "NA" << '\n';
			else
				cout << '#' << i + 1 << ' ' << "YYMM" << '\n';
		}
		else {
			if(sec > 12 || sec == 0)
				cout << '#' << i + 1 << ' ' << "MMYY" << '\n';
			else
				cout << '#' << i + 1 << ' ' << "AMBIGUOUS" << '\n';
		}
	}
	return 0;
}