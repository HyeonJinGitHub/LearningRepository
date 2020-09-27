import sys
from collections import deque

def bfs(now):
    q = deque()
    q.append(now)
    dist = [0] * (F + 1)
    check = [False] * (F + 1)
    check[now] = True
    while q:
        now = q.popleft()
        if now == G:
            print(dist[now])
            return
        if now + U <= F and not check[now + U]:
            q.append(now + U)
            dist[now + U] = dist[now] + 1
            check[now + U] = True
        if now - D > 0 and not check[now - D]:
            q.append(now - D)
            check[now - D] = True
            dist[now - D] = dist[now] + 1
    print('use the stairs')

if __name__ == '__main__':
    input = sys.stdin.readline()
    F, S, G, U, D = map(int, input.split())
    bfs(S)