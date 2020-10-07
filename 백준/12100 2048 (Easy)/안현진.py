import sys
from copy import deepcopy

def print_max(arr):
    global res
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            res = max(res, arr[i][j])

def dfs(tmp_board, n):
    if n == 5:
        print_max(tmp_board)
        return
    dfs(move_left(deepcopy(tmp_board)), n + 1)
    dfs(move_right(deepcopy(tmp_board)), n + 1)
    dfs(move_up(deepcopy(tmp_board)), n + 1)
    dfs(move_down(deepcopy(tmp_board)), n + 1)

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
                    p -= 1
                    x = 0
                else:
                    board[i][p] = x
                    p -= 1
                    x = board[i][j]
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
                    p += 1
                    x = 0
                else:
                    board[p][i] = x
                    p += 1
                    x = board[j][i]
            board[j][i] = 0
        if x != 0: board[p][i] = x
    return board

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
                    p -= 1
                    x = 0
                else:
                    board[p][i] = x
                    p -= 1
                    x = board[j][i]
            board[j][i] = 0
        if x != 0: board[p][i] = x
    return board

if __name__ == '__main__':
    N = int(input())
    a = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    res = 0
    dfs(a, 0)
    print(res)