import sys
from collections import deque

def desc(n, m):
    if n == m:
        return
    desc(n, via[m])
    print(how[m], end='')
def bfs(now):
    q = deque()
    q.append(now)
    dist[now] = 0
    via[now] = -1
    check[now] = True
    while q:
        now = q.popleft()

        next = (now * 2) % 10000
        if not check[next]:
            check[next] = True
            dist[next] = dist[now] + 1
            how[next] = 'D'
            via[next] = now
            q.append(next)
        next = now - 1
        if next == -1:
            next= 9999
        if not check[next]:
            check[next] = True
            dist[next] = dist[now] + 1
            how[next] = 'S'
            via[next] = now
            q.append(next)
        next = (now % 1000) * 10 + now // 1000
        if not check[next]:
            check[next] = True
            dist[next] = dist[now] + 1
            how[next] = 'L'
            via[next] = now
            q.append(next)
        next = (now % 10) * 1000 + now // 10
        if not check[next]:
            check[next] = True
            dist[next] = dist[now] + 1
            how[next] = 'R'
            via[next] = now
            q.append(next)

if __name__ == '__main__':
    MAX = 10001
    sys.setrecursionlimit(MAX)
    N = int(sys.stdin.readline())
    for _ in range(N):
        n, m = map(int, input().split())
        check = [False] * MAX
        via = [-1] * MAX
        how = [''] * MAX
        dist = [-1] * MAX
        bfs(n)
        desc(n, m)
        print()