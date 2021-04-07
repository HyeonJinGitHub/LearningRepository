import sys

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def dfs(x, y):
    if x == M - 1 and y == N - 1:
        return 1
    res = 0
    for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < M and 0 <= ny < N:
            if board[nx][ny] < board[x][y]:
                if dp[nx][ny] >= 0:
                    res += dp[nx][ny]
                else:
                    res += dfs(nx, ny)
    dp[x][y] = res
    return res

if __name__ == '__main__':
    M, N = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
    dp = [[-1] * N for _ in range(M)]
    dp[0][0] = 0
    print(dfs(0, 0))