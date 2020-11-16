import sys
from copy import deepcopy

def dfs(tmp_board, n):
    global res
    if n == 5:
        for i in range(N):
            for j in range(N):
                res = max(res, tmp_board[i][j])
        return
    dfs(move_up(deepcopy(tmp_board)), n + 1)
    dfs(move_down(deepcopy(tmp_board)), n + 1)
    dfs(move_right(deepcopy(tmp_board)), n + 1)
    dfs(move_left(deepcopy(tmp_board)), n + 1)

def move_down(board):
    for i in range(N):
        p = N - 1
        x = 0
        for j in range(N - 1, -1, -1):
            if board[j][i] == 0: continue
            if x == 0:
                x = board[j][i]
            else:
                if x == board[j][i]:
                    board[p][i] = x * 2
                    x = 0
                    p -= 1
                else:
                    board[p][i] = x
                    x = board[j][i]
                    p -= 1
            board[j][i] = 0
        if x != 0: board[p][i] = x
    return board

def move_right(board):
    for i in range(N):
        p = N - 1
        x = 0
        for j in range(N - 1, -1, -1):
            if board[i][j] == 0: continue
            if x == 0:
                x = board[i][j]
            else:
                if x == board[i][j]:
                    board[i][p] = x * 2
                    x = 0
                    p -= 1
                else:
                    board[i][p] = x
                    x = board[i][j]
                    p -= 1
            board[i][j] = 0
        if x != 0: board[i][p] = x
    return board

def move_left(board):
    for i in range(N):
        p = 0
        x = 0
        for j in range(N):
            if board[i][j] == 0: continue
            if x == 0:
                x = board[i][j]
            else:
                if x == board[i][j]:
                    board[i][p] = x * 2
                    x = 0
                    p += 1
                else:
                    board[i][p] = x
                    x = board[i][j]
                    p += 1
            board[i][j] = 0
        if x != 0: board[i][p] = x
    return board

def move_up(board):
    for i in range(N):
        p = 0
        x = 0
        for j in range(N):
            if board[j][i] == 0: continue
            if x == 0:
                x = board[j][i]
            else:
                if x == board[j][i]:
                    board[p][i] = x * 2
                    x = 0
                    p += 1
                else:
                    board[p][i] = x
                    x = board[j][i]
                    p += 1
            board[j][i] = 0
        if x != 0: board[p][i] = x
    return board

if __name__ == '__main__':
    N = int(input())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    res = 0
    dfs(board, 0)
    print(res)