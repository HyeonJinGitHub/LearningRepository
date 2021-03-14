import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(h):
    q = deque([(0, 0)])
    board[0][0] = h

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k],
            if 0 <= nx < N + 2 and 0 <= ny < M + 2:
                if board[nx][ny] < h:
                    board[nx][ny] = h
                    q.append((nx, ny))

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[0] * (M + 2)] + [[0] + list(map(int, sys.stdin.readline().rstrip())) + [0] for _ in range(N)] + [[0] * (M + 2)]
    ans, mx = 0, 0

    for i in range(1, N + 1):
        for j in range(1, M + 1):
            mx = max(mx, board[i][j])
    for h in range(1, mx + 1):
        bfs(h)
        for i in range(1, N + 1):
            for j in range(1, M + 1):
                if board[i][j] < h:
                    board[i][j] += 1
                    ans += 1
    print(ans)
