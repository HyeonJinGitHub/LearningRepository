import sys
from collections import deque

def bfs(x):
    check = [False] * 100001
    MAX = 100000
    dist = [0] * 100001
    q = deque()
    q.append(x)
    check[x] = True

    while q:
        x = q.popleft()
        if x == K:
            print(dist[x])
            return
        if x * 2 <= MAX:
            if not check[x * 2]:
                check[x * 2] = True
                dist[x * 2] = dist[x]
                q.append(x * 2)
        if 0 <= x - 1:
            if not check[x - 1]:
                check[x - 1] = True
                dist[x - 1] = dist[x] + 1
                q.append(x - 1)
        if x + 1 <= MAX:
            if not check[x + 1]:
                check[x + 1] = True
                dist[x + 1] = dist[x] + 1
                q.append(x + 1)

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    bfs(N)