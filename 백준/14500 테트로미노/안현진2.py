import sys

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    ans = 0
    # 1
    for i in range(N):
        for j in range(M - 3):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i][j + 3]
            ans = max(ans, tmp)
    # 2
    for i in range(N - 3):
        for j in range(M):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 3][j]
            ans = max(ans, tmp)
    # 3
    for i in range(N - 1):
        for j in range(M - 1):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i + 1][j] + board[i + 1][j + 1]
            ans = max(ans, tmp)
    # 4
    for i in range(N - 2):
        for j in range(M - 1):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 2][j + 1]
            ans = max(ans, tmp)
    # 5
    for i in range(N - 1):
        for j in range(M - 2):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i + 1][j]
            ans = max(ans, tmp)
    # 6
    for i in range(N - 2):
        for j in range(M - 1):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i + 1][j + 1] + board[i + 2][j + 1]
            ans = max(ans, tmp)
    # 7
    for i in range(N - 1):
        for j in range(2, M):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 1][j - 1] + board[i + 1][j - 2]
            ans = max(ans, tmp)
    # 8
    for i in range(N - 2):
        for j in range(1, M):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 2][j - 1]
            ans = max(ans, tmp)
    # 9
    for i in range(N - 1):
        for j in range(M - 2):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 1][j + 1] + board[i + 1][j + 2]
            ans = max(ans, tmp)
    # 10
    for i in range(N - 2):
        for j in range(M - 1):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i][j + 1]
            ans = max(ans, tmp)
    # 11
    for i in range(N - 1):
        for j in range(M - 2):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i + 1][j + 2]
            ans = max(ans, tmp)
    # 12
    for i in range(N - 2):
        for j in range(M - 1):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 1][j + 1] + board[i + 2][j + 1]
            ans = max(ans, tmp)
    # 13
    for i in range(N - 1):
        for j in range(1, M - 1):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i + 1][j] + board[i + 1][j - 1]
            ans = max(ans, tmp)
    # 14
    for i in range(N - 2):
        for j in range(1, M):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 1][j - 1] + board[i + 2][j - 1]
            ans = max(ans, tmp)
    # 15
    for i in range(N - 1):
        for j in range(M - 2):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i + 1][j + 1] + board[i + 1][j + 2]
            ans = max(ans, tmp)
    # 16
    for i in range(N - 1):
        for j in range(M - 2):
            tmp = 0
            tmp += board[i][j] + board[i][j + 1] + board[i][j + 2] + board[i + 1][j + 1]
            ans = max(ans, tmp)
    # 17
    for i in range(N - 2):
        for j in range(1, M):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 1][j - 1]
            ans = max(ans, tmp)
    # 18
    for i in range(N - 1):
        for j in range(1, M - 1):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 1][j - 1] + board[i + 1][j + 1]
            ans = max(ans, tmp)
    # 19
    for i in range(N - 2):
        for j in range(M - 1):
            tmp = 0
            tmp += board[i][j] + board[i + 1][j] + board[i + 2][j] + board[i + 1][j + 1]
            ans = max(ans, tmp)
    print(ans)