import sys

def bfs(i, check, adj):
    q = [i]
    check[i] = True
    ret = 0
    for d in q:
        for y in adj[d]:
            if not check[y]:
                check[y] = True
                q.append(y)
                ret += 1
    return ret

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    adj_in = [[] for _ in range(N)]
    adj_out = [[] for _ in range(N)]
    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        adj_in[u - 1].append(v - 1)
        adj_out[v - 1].append(u - 1)
    ans = 0
    for i in range(N):
        check = [False] * N
        if bfs(i, check, adj_in) + bfs(i, check, adj_out) + 1 == N:
            ans += 1
    print(ans)