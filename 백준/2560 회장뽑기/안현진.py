import sys
from collections import deque
def bfs(x):
    q = deque([x])
    dist = [0] * (N + 1)
    check = [False] * (N + 1)
    cnt = -1
    check[x] = True
    while q:
        size = len(q)
        cnt += 1
        for _ in range(size):
            x = q.popleft()
            for y in a[x]:
                if dist[y] == 0 and not check[y]:
                    dist[y] = dist[x] + 1
                    q.append(y)
                    check[y] = True
    return cnt
if __name__ == '__main__':
    N = int(input())
    a = [[] for _ in range(N + 1)]
    result = 1000000
    res = []
    while True:
        u, v =  map(int, sys.stdin.readline().split())
        if u == -1 and v == -1:
            break
        a[u].append(v)
        a[v].append(u)

    for i in range(1, N + 1):
        score = bfs(i)
        if score < result:
            res = []
            result = score
            res.append(i)
        elif score == result:
            res.append(i)
    print(result, len(res))
    for s in res:
        print(s, end=' ')