import sys

if __name__ == '__main__':
    check = [[False] * 8 for _ in range(8)]
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(8)]
    res = 0
    for i in range(8):
        for j in range(8):
            if i % 2 == 0:
                if j % 2 == 1:
                    check[i][j] = True
            else:
                if j % 2 == 0:
                    check[i][j] = True
    for i in range(8):
        for j in range(8):
            if not check[i][j] and board[i][j] == 'F':
                res += 1
    print(res)