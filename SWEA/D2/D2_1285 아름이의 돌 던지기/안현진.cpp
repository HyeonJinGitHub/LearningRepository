#include <iostream>

using namespace std;

int main() {
	int t, n;
	cin >> t;
	for (int i = 0; i < t; ++i) {
		int arr[1001];
		int dis = 100001, count = 0, tmp = 0;
		cin >> n;
		for (int j = 0; j < n; ++j) 
			cin >> arr[j];
		for (int j = 0; j < n; ++j) {
			tmp = abs(arr[j]);
			if (tmp < dis){
				dis = tmp;
				count = 1;
			}
			else if (tmp == dis)
				count += 1;
		}
		cout << '#' << i + 1 << ' ' << dis << ' ' << count << '\n';
	}
	return 0;
	
}