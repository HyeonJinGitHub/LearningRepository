import sys
from collections import deque

def shift(seq, n):
    n = n % len(seq)
    return seq[n:] + seq[:n]

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()
if __name__ == '__main__':
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    N, M, K = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    for _ in range(K):
        x, d, k = map(int, sys.stdin.readline().split())
        for i in range(x-1, N, x):
            if d == 0:
                board[i] = shift(board[i], -k)
            else:
                board[i] = shift(board[i], k)
        t = False
        board_sum = 0
        board_len = 0
        q = deque()
        q1 = deque()

        for i in range(N*M):
            r, c = i // M, i % M
            flag = False
            point = board[r][c]

            if point == 0: continue
            for k in range(4):
                nr, nc = r + dx[k], c + dy[k]
                if nc < 0: nc = M -1
                if nc >= M: nc = 0
                if 0 <= nr < N and 0 <= nc < M:
                    if point != 0 and board[nr][nc] != 0:
                        if point == board[nr][nc]:
                            t = True
                            flag = True
                            q1.append((nr, nc))
            if flag:
                q1.append((r, c))
        while q1:
            q1_x, q1_y = q1.popleft()
            board[q1_x][q1_y] = 0

        if not t:
            for i in range(N * M):
                r, c = i // M, i % M
                if board[r][c] == 0: continue
                if board[r][c] != 0:
                    board_sum += board[r][c]
                    board_len += 1
                    q.append((r, c))
            if board_len != 0:
                mean = board_sum / board_len
            while q:
                q_x, q_y = q.popleft()
                if board[q_x][q_y] > mean:
                    board[q_x][q_y] -= 1
                elif board[q_x][q_y] < mean:
                    board[q_x][q_y] += 1
    res = 0
    for i in range(N*M):
        r, c = i // M, i % M
        if board[r][c] != 0:
            res += board[r][c]
    print(res)
