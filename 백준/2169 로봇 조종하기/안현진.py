import sys

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]

    dp = [[-100000000] * M for _ in range(N)]
    left = [[-100000000] * M for _ in range(N)]
    right = [[-100000000] * M for _ in range(N)]

    dp[0][0] = board[0][0]

    for j in range(1, M):
        dp[0][j] = dp[0][j-1] + board[0][j]

    for i in range(1, N):
        left[i][0] = dp[i-1][0] + board[i][0]
        for j in range(1, M):
            left[i][j] = max(dp[i-1][j] + board[i][j], left[i][j-1] + board[i][j])
        right[i][M-1] = dp[i-1][M-1] + board[i][M-1]
        for j in range(M - 2, -1, -1):
            right[i][j] = max(dp[i-1][j] + board[i][j], right[i][j + 1] + board[i][j])
        for j in range(M):
            dp[i][j] = max(right[i][j], left[i][j])
    print(dp[N-1][M-1])