import sys

if __name__ == '__main__':
    N, M = list(map(int, sys.stdin.readline().split()))
    arr = [[int(x) for x in sys.stdin.readline().split()] for y in range(N)]
    res = 0
    # 1
    for i in range(N):
        for j in range(M - 3):
            a = 0
            a += (arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i][j+3])
            if a > res: res = a
    # 2
    for i in range(N - 3):
        for j in range(M):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j])
            if a > res: res = a
    # 3
    for i in range(N - 1):
        for j in range(M - 1):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i][j + 1])
            if a > res: res = a
    # 4
    for i in range(N - 2):
        for j in range(M - 1):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 2][j + 1])
            if a > res: res = a
    # 5
    for i in range(N - 1):
        for j in range(M - 2):
            a = 0
            a += (arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j])
            if a > res: res = a
    # 6
    for i in range(N - 2):
        for j in range(M - 1):
            a = 0
            a += (arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1])
            if a > res: res = a
    # 7
    for i in range(N - 1):
        for j in range(2, M):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 1][j - 1] + arr[i + 1][j - 2])
            if a > res: res = a
    # 8
    for i in range(N - 2):
        for j in range(1, M):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 2][j - 1])
            if a > res: res = a
    # 9
    for i in range(N - 1):
        for j in range(M - 2):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2])
            if a > res: res = a
    # 10
    for i in range(N - 2):
        for j in range(M - 1):
            a = 0
            a += (arr[i][j] + arr[i][j + 1] + arr[i + 1][j] + arr[i + 2][j])
            if a > res: res = a
    # 11
    for i in range(N - 1):
        for j in range(M - 2):
            a = 0
            a += (arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 2])
            if a > res: res = a
    # 12
    for i in range(N - 2):
        for j in range(M - 1):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j + 1])
            if a > res: res = a
    # 13
    for i in range(N - 1):
        for j in range(1, M - 1):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 1][j - 1] + arr[i][j + 1])
            if a > res: res = a
    # 14
    for i in range(N - 2):
        for j in range(1, M):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 1][j - 1] + arr[i + 2][j - 1])
            if a > res: res = a
    # 15
    for i in range(N - 1):
        for j in range(M - 2):
            a = 0
            a += (arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j + 2])
            if a > res: res = a
    # 16
    for i in range(N - 1):
        for j in range(M - 2):
            a = 0
            a += (arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1])
            if a > res: res = a
    # 17
    for i in range(N - 2):
        for j in range(1, M):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j - 1])
            if a > res: res = a
    # 18
    for i in range(N - 1):
        for j in range(1, M - 1):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 1][j - 1] + arr[i + 1][j + 1])
            if a > res: res = a
    # 19
    for i in range(N - 2):
        for j in range(M - 1):
            a = 0
            a += (arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j + 1])
            if a > res: res = a
    sys.stdout.writelines(str(res))