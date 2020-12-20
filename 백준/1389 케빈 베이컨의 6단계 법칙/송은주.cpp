#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;

int dist[110];
vector<int>adj[110];

void bfs(int i){
    queue<int>q;
    q.push(i);
    dist[i] = 0;

    while(!q.empty()){
        int k = q.front();
        q.pop();

        for(int a=0; a<adj[k].size(); ++a){
            int temp = adj[k][a];
            if(dist[temp]==-1){
                dist[temp] = dist[k] + 1;
                q.push(temp);
            }
        }
    }

}

void print(){
    for(int i:dist){
        cout << i << '\t';
    }
    cout << '\n';
    return;
}
int main(){
    cin >> N >> M;
    for(int i=0; i<M; ++i){
        int a, b;
        cin >>a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    vector<pair<int, int> >result;

    for(int i=1; i<=N; ++i){
        memset(dist, -1, sizeof(dist));
        bfs(i); int res = 0;
        //print();
        for(int j=1; j<=N; ++j){
            if(j==i) continue;
            res += dist[j];
        }
        result.push_back(make_pair(res, i));
    }
    sort(result.begin(), result.end());

    cout << result[0].second;


    return 0;
}
