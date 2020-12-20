import sys
from collections import deque

def bfs(x):
    q = deque([x])
    check[x] = True
    m = 1

    for i in range(N):
        if not q:
            print(0)
            exit(0)
        x = q.popleft()
        if x != b[i]:
            print(0)
            exit(0)
        cnt = 0
        for y in a[x]:
            if not check[y]:
                parent[y] = x
                cnt += 1
        for j in range(cnt):
            if m + j >= N or parent[b[m + j]] != x:
                print(0)
                exit(0)
            check[b[m + j]] = True
            q.append(b[m + j])
        m += cnt
    print(1)

if __name__ == '__main__':
    N = int(input())
    a = [[] for _ in range(N)]
    for _ in range(N - 1):
        u, v = map(int, sys.stdin.readline().split())
        a[u - 1].append(v - 1)
        a[v - 1].append(u - 1)
    b = list(map(int, sys.stdin.readline().split()))
    b = [x - 1 for x in b]

    parent = [-1] * N
    check = [False] * N
    bfs(0)