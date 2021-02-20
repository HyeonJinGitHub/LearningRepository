import sys
from collections import deque

dx = [-2, -2, 0, 0, 2, 2]
dy = [-1, 1, -2, 2, -1, 1]

def bfs(x, y):
    dist = [[-1] * N for _ in range(N)]
    q = deque([(x, y)])
    dist[x][y] = 0

    while q:
        x, y = q.popleft()
        if x == r2 and y == c2:
            return dist[x][y]
        for k in range(6):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if dist[nx][ny] == -1:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    return -1

if __name__ == '__main__':
    N = int(input())
    r1, c1, r2, c2 = map(int, sys.stdin.readline().split())
    print(bfs(r1, c1))