import sys

if __name__ == '__main__':
    n, m = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(n)]

    for i in range(1, n):
        for j in range(1, m):
            mn = min(board[i - 1][j - 1], board[i][j - 1], board[i - 1][j])
            if not board[i][j] or not mn: continue
            board[i][j] = max(board[i][j], mn + 1)
    ans = 0
    for i in range(n):
        for j in range(m):
            ans = max(ans, board[i][j])
    print(ans ** 2)