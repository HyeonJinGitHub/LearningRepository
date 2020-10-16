import sys

def solution(board, r, c, d):
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    x, y = r, c

    while True:
        if board[x][y] == 0:
            board[x][y] = 2

        if board[x+dx[d]][y+dy[d]] != 0 and board[x+dx[(d+1) % 4]][y+dy[(d+1) % 4]] != 0 and board[x+dx[(d+2) % 4]][y+dy[(d+2) % 4]] != 0 and board[x+dx[(d+3) % 4]][y + dy[(d+3) % 4]] != 0:
            if board[x-dx[d]][y-dy[d]] == 1:
                break
            else:
                x -= dx[d]
                y -= dy[d]
        else:
            d = (d + 3) % 4
            nx, ny = x + dx[d], y + dy[d]
            if board[nx][ny] == 0:
                x, y = nx, ny

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    r, c, d = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    solution(board, r, c, d)
    cnt = 0
    for i in range(N):
        for j in range(M):
            if board[i][j] == 2:
                cnt += 1
    print(cnt)