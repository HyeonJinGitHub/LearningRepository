import sys
from collections import deque

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque([(x, y)])
    dist = [[0] * M for _ in range(N)]
    dist[x][y] = 1

    while q:
        x, y = q.popleft()
        if x == N -1 and y == M -1:
            print(dist[x][y])
            exit(0)
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if board[nx][ny] == 1 and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(N)]
    bfs(0, 0)