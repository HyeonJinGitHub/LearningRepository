import sys
from collections import deque

def bfs(x, y, d):
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    q = deque([(x, y, d)])
    board[x][y] = 2

    while q:
        x, y, d = q.popleft()
        flag = False
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if board[nx][ny] == 0:
                    flag = True
        if not flag:
            nx, ny = x - dx[d], y - dy[d]
            if board[nx][ny] == 1: break
            q.append((nx, ny, d))
            continue
        nd = (d + 3) % 4
        nx, ny = x + dx[nd], y + dy[nd]

        if nx < 0 or nx >= N or ny < 0 or ny >= M or board[nx][ny] == 1 or board[nx][ny] == 2:
            q.append((x, y, nd))
            continue
        if board[nx][ny] == 0:
            board[nx][ny] = 2
            q.append((nx, ny, nd))


if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    r, c, d = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    ans = 0
    bfs(r, c, d)
    for i in range(N):
        for j in range(M):
            if board[i][j] == 2:
                ans += 1
    print(ans)