import sys
from collections import deque

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    dist = [[0] * M for _ in range(N)]
    dist1 = [[0] * M for _ in range(N)]
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if a[nx][ny] == 2:
                    q1 = deque()
                    q.append((nx, ny))
                    q1.append((nx, ny))
                    dist1[nx][ny] = dist[x][y] + 1
                    dist[nx][ny] = dist1[nx][ny]
                    while q1:
                        tm_x, tm_y = q1.popleft()
                        for k1 in range(4):
                            nnx, nny = tm_x + dx[k1], tm_y + dy[k1]
                            if 0 <= nnx < N and 0 <= nny < M:
                                if dist1[nnx][nny] == 0:
                                    dist1[nnx][nny] = dist1[tm_x][tm_y] + 1
                                    q1.append((nnx, nny))
                elif a[nx][ny] == 0 and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    res1 = dist[N-1][M-1]
    res2 = dist1[N-1][M-1]
    if res1 != 0 and res2 != 0:
        return min(res1, res2)
    elif res1 == 0 and res2 != 0:
        return res2
    elif res2 == 0 and res1 != 0:
        return res1
    else:
        return 0

if __name__ == '__main__':
    N, M, T = map(int, input().split())
    a = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    res = bfs(0, 0)
    if res > T or res == 0:
        print('Fail')
    else:
        print(res)