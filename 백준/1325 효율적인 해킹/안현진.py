import sys
from collections import deque

def bfs(start):
    check = [False] * (N + 1)
    q = deque()
    dis = 0
    q.append(start)
    check[start] = True
    while q:
        now = q.popleft()
        dis += 1
        for y in a[now]:
            if not check[y]:
                check[y] = True
                q.append(y)
    return dis
if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    a = [[] for _ in range(N + 1)]
    mxd = 0
    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        a[v].append(u)
    for i in range(N + 1):
        a[i].sort()
    for i in range(1, N + 1):
        if a[i]:
            tmp = bfs(i)
            if tmp >= mxd:
                if mxd < tmp:
                    result = []
                mxd = tmp
                result.append(i)
    print(*result)