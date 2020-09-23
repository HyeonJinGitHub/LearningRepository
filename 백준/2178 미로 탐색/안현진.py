from collections import deque
import sys

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    dist = [[0 for x in range(M)] for y in range(N)]
    q = deque()
    q.append((x, y))
    dist[x][y] = 1
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if a[nx][ny] == 1 and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    print(dist[N - 1][M - 1])

if __name__ == '__main__':
    N, M = map(int, input().split())
    a = [[0 for x in range(M)] for y in range(N)]
    for i in range(N):
        tmp = sys.stdin.readline().rstrip()
        for j in range(M):
            a[i][j] = int(tmp[j])
    bfs(0, 0)