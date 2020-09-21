import sys
from collections import deque

def bfs(start):
    check = [False] * (N + 1)
    dist = [0] * (N + 1)
    q = deque()
    arr = []
    check[start] = True
    q.append(start)
    while q:
        new = q.popleft()
        for y in a[new]:
            if not check[y]:
                check[y] = True
                dist[y] = dist[new] + 1
                q.append(y)
            if dist[y] == K:
                arr.append(y)
    res = list(set(arr))
    res.sort()
    if len(res) == 0:
        print(-1)
    else:
        while res:
            print(res.pop(0))
if __name__ == '__main__':
    N, M, K, X = map(int, sys.stdin.readline().split())
    a = [[] for _ in range(N + 1)]
    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        a[u].append(v)
    bfs(X)