import sys
from collections import deque
def bfs(N):
    global dist
    q = deque([N])
    check = [False] * MAX
    dist[N] = 0
    while q:
        x = q.popleft()
        if x == K:
            print(dist[K])
            return
        if x + 1 < MAX:
            if not check[x + 1]:
                check[x + 1] = True
                dist[x + 1] = dist[x] + 1
                q.append(x + 1)
        if x - 1 >= 0:
            if not check[x - 1]:
                check[x - 1] = True
                dist[x - 1] = dist[x] + 1
                q.append(x - 1)
        if x * 2 < MAX:
            if not check[x * 2]:
                check[x * 2] = True
                dist[x * 2] = dist[x] + 1
                q.append(x * 2)

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    MAX = 100001
    dist = [-1] * MAX

    bfs(N)