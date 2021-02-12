import sys
from itertools import permutations
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    original = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    cmd = []
    ans = sys.maxsize
    for _ in range(K):
        r, c, s = map(int, sys.stdin.readline().split())
        cmd.append((r, c, s))
    for calculate in permutations(cmd, K):
        board = [x[:] for x in original]
        for y in calculate:
            r, c, s = y[0], y[1], y[2]
            size = 2 * s + 1
            tmp_board = [[0] * size for _ in range(size)]
            for i in range(size):
                for j in range(size):
                    tmp_board[i][j] = board[r - s - 1 + i][c - s - 1 + j]
            check = [[False] * size for _ in range(size)]
            for i in range(size // 2):
                x, y = i, i
                d = 0
                l, q = [(x, y)], deque()
                while not check[x][y]:
                    check[x][y] = True
                    q.append(tmp_board[x][y])
                    nx, ny = x + dx[d], y + dy[d]
                    if (nx, ny) == (i, i): break
                    if nx < 0 or nx >= size or ny < 0 or ny >= size or check[nx][ny]:
                        d = (d + 1) % 4
                        nx, ny = x + dx[d], y+ dy[d]
                    x, y = nx, ny
                    l.append((nx, ny))
                q.rotate(1)
                for j in range(len(l)):
                    lx, ly = l[j]
                    tmp_board[lx][ly] = q.popleft()
            for i in range(size):
                for j in range(size):
                    board[r - s - 1 + i][c - s - 1 + j] = tmp_board[i][j]
        for i in range(N):
            ans = min(ans, sum(board[i]))
    print(ans)
