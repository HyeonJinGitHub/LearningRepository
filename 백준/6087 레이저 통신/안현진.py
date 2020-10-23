import sys
from collections import deque

def bfs(start_x, start_y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque([(start_x, start_y)])
    mirrors[start_x][start_y] = 0
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            while 0 <= nx < H and 0 <= ny < W:
                if mirrors[nx][ny] == '*': break
                if mirrors[nx][ny] == -1:
                    mirrors[nx][ny] = mirrors[x][y] + 1
                    q.append((nx, ny))
                nx += dx[k]
                ny += dy[k]

if __name__ == '__main__':
    W, H = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(H)]
    mirrors = [[] for _ in range(H)]
    C = []
    for i in range(H):
        for j in range(W):
            if board[i][j] == 'C':
                C.append((i, j))
            if board[i][j] != '*':
                mirrors[i].append(-1)
            else:
                mirrors[i].append('*')
    bfs(C[0][0], C[0][1])
    print(mirrors[C[1][0]][C[1][1]] - 1)