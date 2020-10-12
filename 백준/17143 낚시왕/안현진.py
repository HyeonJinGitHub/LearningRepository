import sys
from collections import defaultdict

def solution(board, shark):
    global eat_shark, people_location
    dx = [-1, 1, 0, 0]
    dy = [0, 0, 1, -1]
    while people_location < C:
        for i in range(R):
            if len(board[i][people_location]) != 0:
                eat_shark += board[i][people_location][0]
                board[i][people_location] = []
                shark[(i, people_location)] = []
                break
        tmp_board = [[[] for _ in range(C)] for _ in range(R)]
        tmp_shark = defaultdict(lambda :[])
        for i in range(R*C):
            x, y = i // C, i % C
            if not board[x][y]: continue
            speed, direction = shark[(x, y)].pop(0)
            tmp = board[x][y].pop(0)
            if direction == 0 or direction == 1:
                speed = speed % ((R - 1) * 2)
                nx = x
                for _ in range(speed):
                    nx += dx[direction]
                    if nx > R - 1:
                        direction = 0
                        nx += dx[direction] * 2
                    if nx < 0:
                        direction = 1
                        nx += dx[direction] * 2
                tmp_board[nx][y].append(tmp)
                tmp_shark[(nx, y)].append((speed, direction))
            if direction == 2 or direction == 3:
                speed = speed % ((C - 1) * 2)
                ny = y
                for _ in range(speed):
                    ny += dy[direction]
                    if ny > C - 1:
                        direction = 3
                        ny += dy[direction] * 2
                    if ny < 0:
                        direction = 2
                        ny += dy[direction] * 2
                tmp_board[x][ny].append(tmp)
                tmp_shark[(x, ny)].append((speed, direction))
        board = tmp_board
        shark = tmp_shark
        for i in range(R*C):
            x, y = i // C, i % C
            tmp, idx = 0, 0
            if len(board[x][y]) == 0: continue

            for j in range(len(board[x][y])):
                if tmp < board[x][y][j]:
                    idx = j
                    tmp = board[x][y][j]
            board[x][y] = []
            board[x][y].append(tmp)
            t = shark[(x, y)]
            max_speed, max_direction = t[idx]
            shark[(x, y)].clear()
            shark[(x, y)].append((max_speed, max_direction))
        people_location += 1
    print(eat_shark)

if __name__ == '__main__':
    R, C, M = map(int, sys.stdin.readline().split())
    board = [[[] for _ in range(C)] for _ in range(R)]
    shark = defaultdict(lambda :[])
    eat_shark = 0
    people_location = 0
    for _ in range(M):
        r, c, s, d, z = map(int, sys.stdin.readline().split())
        board[r-1][c-1].append(z)
        shark[(r - 1, c - 1)].append((s, d - 1))
    if M == 0:
        print(0)
    else:
        solution(board, shark)