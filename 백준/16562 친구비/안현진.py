import sys

def dfs(x):
    check[x] = True
    tmp.append(money[x])
    for y in a[x]:
        if not check[y]:
            dfs(y)
if __name__ == '__main__':
    sys.setrecursionlimit(11000)
    N, M, k = map(int, sys.stdin.readline().split())
    money = [0] + list(map(int, sys.stdin.readline().split()))
    check = [False] * (N + 1)
    a = [[] for _ in range(N + 1)]
    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        a[u].append(v)
        a[v].append(u)
    res = 0
    for i in range(1, N + 1):
        if not check[i]:
            tmp = []
            dfs(i)
            if len(tmp) != 0:
                res += min(tmp)
    if res > k:
        print('Oh no')
    else:
        print(res)
