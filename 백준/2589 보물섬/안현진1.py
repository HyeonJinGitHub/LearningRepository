import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y):
    global ans
    q = deque([(x, y)])
    check = [[False] * M for _ in range(N)]
    dist = [[0] * M for _ in range(N)]
    check[x][y] = True

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if not check[nx][ny] and board[nx][ny] == 'L':
                    q.append((nx, ny))
                    dist[nx][ny] = dist[x][y] + 1
                    check[nx][ny] = True
                    ans = max(ans, dist[nx][ny])

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    ans = 0
    for i in range(N):
        for j in range(M):
            if board[i][j] == 'L':
                bfs(i, j)
    print(ans)