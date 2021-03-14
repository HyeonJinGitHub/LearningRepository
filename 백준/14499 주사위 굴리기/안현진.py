import sys

if __name__ == '__main__':
    N, M, x, y, K = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    cmd = list(map(int, sys.stdin.readline().split()))
    dice = [0, 0, 0, 0, 0, 0]
    for c in cmd:
        if c == 1:
            nx, ny = x, y + 1
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            tmp = dice[2]
            dice[2] = dice[0]
            dice[0] = dice[3]
            dice[3] = dice[5]
            dice[5] = tmp
        elif c == 2:
            nx, ny = x, y - 1
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            tmp = dice[3]
            dice[3] = dice[0]
            dice[0] = dice[2]
            dice[2] = dice[5]
            dice[5] = tmp
        elif c == 3:
            nx, ny = x - 1, y
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            tmp = dice[1]
            dice[1] = dice[0]
            dice[0] = dice[4]
            dice[4] = dice[5]
            dice[5] = tmp
        elif c == 4:
            nx, ny = x + 1, y
            if nx < 0 or nx >= N or ny < 0 or ny >= M: continue
            tmp = dice[4]
            dice[4] = dice[0]
            dice[0] = dice[1]
            dice[1] = dice[5]
            dice[5] = tmp
        if board[nx][ny] == 0:
            board[nx][ny] = dice[5]
        else:
            dice[5] = board[nx][ny]
            board[nx][ny] = 0
        x, y = nx, ny
        print(dice[0])




