import sys
from collections import deque

def dfs(start):
    global check
    check[start] = True
    print(start, end=' ')
    for y in a[start]:
        if not check[y]:
            check[y] = True
            dfs(y)
def bfs(start):
    check = [False] * (n + 1)
    check[start] = True
    q = deque()
    q.append(start)
    while q:
        next = q.popleft()
        print(next, end=' ')
        for y in a[next]:
            if not check[y]:
                check[y] = True
                q.append(y)
if __name__ == '__main__':
    n, m, start = map(int, sys.stdin.readline().split())
    a = [[] for _ in range(n + 1)]
    check = [False] * (n + 1)
    for _ in range(m):
        u, v = map(int, sys.stdin.readline().split())
        a[u].append(v)
        a[v].append(u)
    for i in range(n):
        a[i].sort()
    dfs(start)
    print()
    bfs(start)