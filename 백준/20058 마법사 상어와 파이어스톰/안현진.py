import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y, tmp_board, group):
    q = deque([(x, y)])
    group[x][y] = group_count
    cnt = 1
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if tmp_board[nx][ny] != 0 and group[nx][ny] == -1:
                    group[nx][ny] = group_count
                    q.append((nx, ny))
                    cnt += 1
    return cnt

def melt(prev_board):
    tmp_board = [[0] * N for _ in range(N)]
    for y in range(N*N):
        r, c = y // N, y % N
        if not prev_board[r][c]: continue
        check = 0
        for k in range(4):
            nr, nc = r + dx[k], c + dy[k]
            if nr < 0 or nr >= N or nc < 0 or nc >= N: check += 1
            if 0 <= nr < N and 0 <= nc < N:
                if not prev_board[nr][nc]: check += 1
        if check >= 2:
            tmp_board[r][c] = prev_board[r][c] - 1
        else: tmp_board[r][c] = prev_board[r][c]
    return tmp_board

def array_shift(prev_board, base):
    tmp_base = 2 ** base
    s_s, s_e = 0, tmp_base
    e_s, e_e = 0, tmp_base
    tmp_board = [[0] * N for _ in range(N)]

    while True:
        for i in range(s_s, s_e):
            for j in range(e_s, e_e):
                tmp_board[i][j] = prev_board[s_s + e_e - (j + 1)][i - s_s + e_s]
        if e_e == N:
            if s_e == N: break
            e_s, e_e = 0, tmp_base
            s_s += tmp_base
            s_e += tmp_base
            continue
        e_s += tmp_base
        e_e += tmp_base
    return tmp_board

if __name__ == '__main__':
    n, Q = map(int, sys.stdin.readline().split())
    N = 2 ** n
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    L = list(map(int, sys.stdin.readline().split()))

    for step in L:
        group = [[-1] * N for _ in range(N)]
        group_count = 1
        max_cnt = 0
        if step != 0:
            board = array_shift(board, step)
        board = melt(board)
        for y in range(N*N):
            r, c = y // N, y % N
            if not board[r][c]: continue
            if board[r][c] != 0 and group[r][c] == -1:
                max_cnt = max(max_cnt, bfs(r, c, board, group))
                group_count += 1

    ans = 0
    for y in range(N*N):
        r, c = y // N, y % N
        ans += board[r][c]
    print(ans)
    print(max_cnt)