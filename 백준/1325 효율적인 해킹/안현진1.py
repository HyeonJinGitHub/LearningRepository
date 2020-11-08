import sys
from collections import deque

def bfs(x):
    q = deque([x])
    check[x] = True
    tmp = 0
    while q:
        x = q.popleft()
        for y in adj_list[x]:
            if not check[y]:
                check[y] = True
                tmp += 1
                q.append(y)
    return tmp

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    adj_list = [[] for _ in range(N + 1)]
    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        adj_list[v].append(u)
    res = []
    for i in range(1, N + 1):
        check = [False] * (N + 1)
        t = bfs(i)
        if not res:
            res.append((t, i))
        else:
            for y in res:
                if t > y[0]:
                    res.clear()
                    res.append((t, i))
                    break
                elif t == y[0]:
                    res.append((t, i))
                    break
    ans = []
    for a in res:
        ans.append(a[1])
    ans.sort()
    for y in ans:
        print(y, end=' ')