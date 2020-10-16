import sys

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()
def solution(board):
    global T
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    res = 0

    while T > 0:
        tmp = [[0] * C for _ in range(R)]
        for i in range(R*C):
            r, c = i // C, i % C
            if board[r][c] < 5: continue
            add_dust_count = 0
            for k in range(4):
                nr, nc = r + dx[k], c + dy[k]
                if 0 <= nr < R and 0 <= nc < C:
                    if board[nr][nc] != -1:
                        tmp[nr][nc] += (board[r][c] // 5)
                        add_dust_count += 1
            board[r][c] -= (board[r][c] // 5) * add_dust_count
        for i in range(R):
            for j in range(C):
                if tmp[i][j] != 0:
                    board[i][j] += tmp[i][j]

        board[cleaner_x - 1][0] = 0 #청소기에 들어올 먼지 삭제
        for i in range(cleaner_x - 1, -1, -1):
            board[i][0] = board[i-1][0]
        for i in range(C - 1):
            board[0][i] = board[0][i+1]
        for i in range(cleaner_x):
            board[i][C - 1] = board[i + 1][C - 1]
        for i in range(C - 1, cleaner_y + 1, -1):
            board[cleaner_x][i] = board[cleaner_x][i-1]
        board[cleaner_x][1] = 0
        cleaner_x1 = cleaner_x + 1
        board[cleaner_x1 + 1][0] = 0
        for i in range(cleaner_x1 + 1, R - 1):
            board[i][0] = board[i + 1][0]
        for i in range(C - 1):
            board[R - 1][i] = board[R - 1][i + 1]
        for i in range(R - 1, cleaner_x1 - 1, -1):
            board[i][C - 1] = board[i - 1][C - 1]
        for i in range(C - 1, cleaner_y + 1, -1):
            board[cleaner_x1][i] = board[cleaner_x1][i - 1]
        board[cleaner_x1][1] = 0
        T -= 1
        if T <= 0:
            for i in range(R):
                res += sum(board[i])
            res += 2
            return res

if __name__ == '__main__':
    R, C, T = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(R)]

    cleaner_x, cleaner_y = 0, 0
    for i in range(R):
        if board[i][0] == -1:
            cleaner_x, cleaner_y = i, 0
            break

    print(solution(board))