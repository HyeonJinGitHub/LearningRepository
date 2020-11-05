import sys
from collections import deque

def dfs(x):
    global visit
    visit[x] = True

    print(x, end=' ')
    for y in a[x]:
        if not visit[y]:
            dfs(y)

def bfs(x):
    q = deque([x])
    check = [False] * (N + 1)
    check[x] = True
    while q:
        x = q.popleft()
        print(x, end=' ')
        for y in a[x]:
            if not check[y]:
                check[y] = True
                q.append(y)

if __name__ == '__main__':
    N, M, V = map(int, sys.stdin.readline().split())
    a = [[] for _ in range(N + 1)]
    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        a[u].append(v)
        a[v].append(u)
    for i in range(1, N + 1):
        a[i].sort()
    visit = [False] * (N + 1)

    dfs(V)
    print()
    bfs(V)