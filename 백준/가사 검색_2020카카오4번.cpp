#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

const int ALPABATS = 26;

class Tri_Node {
private:
	Tri_Node* child[ALPABATS];
	int count;
public:
	Tri_Node() {
		for (int i = 0; i < ALPABATS; ++i)
			child[i] = NULL;
		count = 1;
	}
	~Tri_Node() {
		for (int i = 0; i < ALPABATS; ++i)
			if (child[i] != NULL)
				delete child[i];
	}
	int tonum(char c) {
		return tolower(c) - 'a';
	}
	void insert(const char* words) {
		if (*words == '\0')
			return;
		int next = tonum(*words);

		if (child[next] == NULL) {
			child[next] = new Tri_Node();
		}
		else
			child[next]->count++;
		child[next]->insert(words + 1);
	}
	int find(const char* words) {
		int next = tonum(*words);
		if (*words == '?') {
			int tmp = 0;
			for (int k = 0; k < 26; ++k) {
				if (child[k] != NULL)
					tmp += child[k]->count;
			}
			return tmp;
		}
		if (child[next] == NULL)
			return 0;
		if (*(words + 1) == '?')
			return child[next]->count;
		return child[next]->find(words + 1);
	}
};

Tri_Node* root[10001];
Tri_Node* reroot[10001];

vector<int> solution(vector<string> words, vector<string> queries) {
	int wsize = words.size();
	int qsize = queries.size();
	Tri_Node tri;
	vector<int> answer(qsize,0);
	
	for (auto& a : words) {
		int size = a.size();

		const char* c = a.c_str();
		if (root[size] == NULL)
			root[size] = new Tri_Node();
		root[size]->insert(c);

		string reversed_string = a;
		reverse(reversed_string.begin(), reversed_string.end());
		
		const char* k = reversed_string.c_str();
		if (reroot[size] == NULL)
			reroot[size] = new Tri_Node();
		reroot[size]->insert(k);
	}
	int idx = 0;
	for (auto& a : queries) {
		int size = a.size();

		if (a[size - 1] == '?') {
			const char* c = a.c_str();
			if (root[size] == NULL) {
				idx++;
				continue;
			}
			else
				answer[idx] = root[size]->find(c);
		}
		else {
			string re = a;
			reverse(re.begin(), re.end());
			const char* k = re.c_str();

			if (reroot[size] == NULL) {
				idx++;
				continue;
			}
			else
				answer[idx] = reroot[size]->find(k);
		}
		idx++;
	}
	return answer;
}
int main() {
	vector<int> v =  solution({ "frodo", "front", "frost", "frozen", "frame", "kakao" }, { "fro??", "????o", "fr???", "fro???", "pro?" });
	for (int i = 0; i < v.size(); ++i)
		cout << v[i] << ' ';
	return 0;
}