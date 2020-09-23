import sys
from collections import deque

def bfs():
    dx = [0, 1, 0, -1]
    dy = [1, 0 ,-1, 0]
    q = deque()
    res = 0
    dist = [[-1 for _ in range(M)] for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if a[i][j] == 1:
                q.append((i, j))
                dist[i][j] = 0
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if a[nx][ny] == 0 and dist[nx][ny] == -1:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    for i in range(N):
        for j in range(M):
            if a[i][j] == 0 and dist[i][j] == -1:
                print(-1)
                return
            else:
                if res < dist[i][j]:
                    res = dist[i][j]
    print(res)

if __name__ == '__main__':
    M, N = map(int, input().split())
    a = [[int(x) for x in sys.stdin.readline().split()] for y in range(N)]
    bfs()