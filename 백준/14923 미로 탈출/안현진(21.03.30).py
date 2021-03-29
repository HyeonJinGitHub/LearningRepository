import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(x, y, z):
    dist = [[[-1] * 2 for _ in range(M)] for _ in range(N)]
    q = deque([(x, y, z)])
    dist[x][y][0] = 0

    while q:
        x, y, z = q.popleft()
        if x == ex - 1 and y == ey - 1:
            if dist[x][y][0] != -1 and dist[x][y][1] != -1:
                return min(dist[x][y][0], dist[x][y][1])
            elif dist[x][y][0] == -1:
                return dist[x][y][1]
            elif dist[x][y][1] == -1:
                return dist[x][y][0]
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if board[nx][ny] == 1:
                    if not z and dist[nx][ny][z + 1] == -1:
                        dist[nx][ny][z + 1] = dist[x][y][z] + 1
                        q.append((nx, ny, z + 1))
                else:
                    if dist[nx][ny][z] == -1:
                        dist[nx][ny][z] = dist[x][y][z] + 1
                        q.append((nx, ny, z))
    return -1

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    hx, hy = map(int, sys.stdin.readline().split())
    ex, ey = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    ans = bfs(hx - 1, hy - 1, 0)
    print(ans)

