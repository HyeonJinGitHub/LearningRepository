import sys
from collections import deque

def move(x, y, dx, dy):
    cnt = 0
    while True:
        nx, ny = x + dx, y + dy
        cnt += 1
        if board[nx][ny] == 'O': return (nx, ny, cnt)
        elif board[nx][ny] == '#': return (x, y, cnt - 1)
        x, y = nx, ny

def bfs(rx, ry, bx, by):
    global dist
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque([(rx, ry, bx, by)])
    dist[rx][ry][bx][by] = 0

    while q:
        rx, ry, bx, by = q.popleft()
        if dist[rx][ry][bx][by] > 10: continue
        if board[rx][ry] == 'O': return dist[rx][ry][bx][by]
        for k in range(4):
            nrx, nry, nrd = move(rx, ry, dx[k], dy[k])
            nbx, nby, nbd = move(bx, by, dx[k], dy[k])
            if board[nbx][nby] == 'O': continue
            if nrx == nbx and nry == nby:
                if nrd > nbd:
                    nrx, nry = nrx - dx[k], nry - dy[k]
                else:
                    nbx, nby = nbx - dx[k], nby - dy[k]
            if dist[nrx][nry][nbx][nby] != -1: continue
            dist[nrx][nry][nbx][nby] = dist[rx][ry][bx][by] + 1
            q.append((nrx, nry, nbx, nby))
    return -1

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    dist = [[[[-1] * M for _ in range(N)] for _ in range(M)] for _ in range(N)]

    for i in range(N):
        for j in range(M):
            if board[i][j] == 'R':
                rx, ry = i, j
            if board[i][j] == 'B':
                bx, by = i, j
    print(bfs(rx, ry, bx, by))