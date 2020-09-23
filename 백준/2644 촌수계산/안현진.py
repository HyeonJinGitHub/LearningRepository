from collections import deque
import sys

def bfs(x):
    dist = [0] * (N + 1)
    q = deque()
    q.append(x)
    while q:
        now = q.popleft()
        for y in a[now]:
            if dist[y] == 0:
                dist[y] = dist[now] + 1
                q.append(y)
    if dist[t2] == 0:
        print(-1)
    else:
        print(dist[t2])

if __name__ == '__main__':
    N = int(input())
    a = [[] for _ in range(N + 1)]
    t1, t2 = map(int, input().split())
    m = int(input())
    for _ in range(m):
        u, v = map(int, sys.stdin.readline().split())
        a[u].append(v)
        a[v].append(u)
    bfs(t1)