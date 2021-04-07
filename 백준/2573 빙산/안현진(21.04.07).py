import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(x, y):
    q = deque([(x, y)])
    group[x][y] = group_cnt
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if board[nx][ny] and group[nx][ny] == -1:
                    group[nx][ny] = group_cnt
                    q.append((nx, ny))

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    ans = 0
    while True:
        tmp_board = [x[:] for x in board]
        group = [[-1] * M for _ in range(N)]
        group_cnt = 0

        for i in range(N):
            for j in range(M):
                if not board[i][j]: continue
                cnt = 0
                for k in range(4):
                    nx, ny = i + dx[k], j + dy[k]
                    if 0 <= nx < N and 0 <= ny < M:
                        if not board[nx][ny]:
                            cnt += 1
                if tmp_board[i][j] - cnt > 0:
                    tmp_board[i][j] -= cnt
                else: tmp_board[i][j] = 0
        board = tmp_board
        for i in range(N):
            for j in range(M):
                if board[i][j] and group[i][j] == -1:
                    bfs(i, j)
                    group_cnt += 1
        ans += 1
        if not group_cnt:
            print(0)
            break
        elif group_cnt != 1:
            print(ans)
            break

