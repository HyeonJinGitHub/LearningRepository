import sys
from itertools import permutations
from collections import deque

def bfs():
    global ans
    dx = [1, -1, 0, 0, 0, 0]
    dy = [0, 0, 1, -1, 0, 0]
    dz = [0, 0, 0, 0, 1, -1]
    dist = [[[-1] * 5 for _ in range(5)] for _ in range(5)]

    q = deque()
    q.append((0, 0, 0))
    dist[0][0][0] = 0

    while q:
        x, y, z = q.popleft()
        if (x, y, z) == (4, 4, 4):
            ans = min(ans, dist[x][y][z])
            if ans == 12:
                print(12)
                exit(0)
            return
        for k in range(6):
            nx, ny, nz = x + dx[k], y + dy[k], z + dz[k]
            if nx < 0 or nx >= 5 or ny < 0 or ny >= 5 or nz < 0 or nz >= 5:
                continue
            if dist[nx][ny][nz] == -1 and b[nx][ny][nz]:
                q.append((nx, ny, nz))
                dist[nx][ny][nz] = dist[x][y][z] + 1

def rotate(k):
    tmp = [[0] * 5 for _ in range(5)]
    for i in range(5):
        for j in range(5):
            tmp[i][j] = b[k][4-j][i]
    b[k] = tmp
def maze(cnt):
    if cnt == 5:
        if b[4][4][4]:
            bfs()
        return
    for i in range(4):
        if b[0][0][0]:
            maze(cnt + 1)
        rotate(cnt)


if __name__ == '__main__':
    ans = sys.maxsize
    board = [[list(map(int, sys.stdin.readline().split())) for _ in range(5)] for _ in range(5)]
    b = [[[0] * 5 for _ in range(5)] for _ in range(5)]

    for d in permutations([0, 1, 2, 3, 4]):
        for i in range(5):
            b[d[i]] = board[i]
        maze(0)
    print(ans if ans != sys.maxsize else -1)