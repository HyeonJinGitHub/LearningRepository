import sys

def arr_move(arr, n):
    n = n % len(arr)
    return arr[n:] + arr[:n]

def solution(n, d):
    left_point, right_point = board[n][6], board[n][2]
    nd = -d
    idx, idx1 = n, n
    for i in range(n + 1, 4):
        ch = board[i][6]
        if ch == right_point:
            break
        right_point = board[i][2]
        idx = i
    for i in range(n -1, -1, -1):
        ch = board[i][2]
        if ch == left_point:
            break
        left_point = board[i][6]
        idx1 = i
    for i in range(n, idx + 1):
        board[i] = arr_move(board[i], nd)
        nd = -nd
    for i in range(n-1, idx1-1, -1):
        board[i] = arr_move(board[i], d)
        d = -d

if __name__ == '__main__':
    board = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(4)]
    for _ in range(int(input())):
        n, d = map(int, sys.stdin.readline().split())
        solution(n - 1, d)
    ans = 0
    for i in range(4):
        if board[i][0] == 1:
            if i == 0:
                ans += 1
            else:
                ans += 2**i
    print(ans)