from collections import deque

def bfs(start):
    check = [False] * (N + 1)
    q = deque()

    q.append(start)
    check[start] = True
    cnt = 0
    while q:
        now = q.popleft()
        for y in a[now]:
            if not check[y]:
                check[y] = True
                cnt += 1
                q.append(y)
    print(cnt)

if __name__ == '__main__':
    N = int(input())
    a = [[] for _ in range(N + 1)]
    n = int(input())
    for _ in range(n):
        u, v = map(int, input().split())
        a[u].append(v)
        a[v].append(u)
    bfs(1)