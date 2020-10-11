import sys
from collections import deque

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end='')
        print()
def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque([(x, y)])
    check[x][y] = True
    dist[x][y] = 0
    q1 = deque()
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if nx < 0: nx = R - 1
            if nx >= R: nx = 0
            if ny < 0: ny = C - 1
            if ny >= C: ny = 0
            if board[nx][ny] == '#': continue
            if nx == d_x and ny == d_y:
                board[x][y] = 'x'
                dist[nx][ny] = dist[x][y] + 1
                print('YES')
                tmp = dist[x][y]
                while q1:
                    tx, ty = q1.pop()
                    if dist[tx][ty] == tmp - 1 and check[tx][ty]:
                        for tt in range(4):
                            ttx, tty = tx + dx[tt], ty + dy[tt]
                            if ttx < 0: ttx = R - 1
                            if ttx >= R: ttx = 0
                            if tty < 0: tty = C - 1
                            if tty >= C: tty = 0
                            if 0 <= ttx < R and 0 <= tty < C:
                                if board[ttx][tty] == 'x':
                                    board[tx][ty] = 'x'
                                    tmp -= 1
                                    break
                print_arr(board)
                return
            if 0 <= nx < R and 0 <= ny < C:
                if board[nx][ny] == '.' and not check[nx][ny]:
                    check[nx][ny] = True
                    q.append((nx, ny))
                    dist[nx][ny] = dist[x][y] + 1
                    q1.append((nx, ny))
    print('NO')

R, C = map(int, sys.stdin.readline().split())
board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
#a = [['.'] * (C + 2)] + [['.'] + board[i] + ['.'] for i in range(R)] + [['.'] * (C + 2)]
check = [[False] * C for _ in range(R)]
dist = [[-1] * C for _ in range(R)]

d_x, d_y = 0, 0
s_x, s_y = 0, 0
for i in range(len(board)):
    for j in range(len(board[i])):
        if board[i][j] == 'M':
            s_x, s_y = i, j
        elif board[i][j] == 'D':
            d_x, d_y = i, j
bfs(s_x, s_y)
#print_arr(check)
#print_arr(dist)