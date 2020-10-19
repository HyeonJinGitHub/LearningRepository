import sys
from itertools import permutations
from collections import deque

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    q = deque([(x, y)])
    dist = [[0] * w for _ in range(h)]
    check = [[False] * w for _ in range(h)]

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < h and 0 <= ny < w:
                if not check[nx][ny] and board[nx][ny] != 'x':
                    check[nx][ny] = True
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    return dist

if __name__ == '__main__':
    while True:
        w, h = map(int, sys.stdin.readline().split())
        if w == 0 and h == 0: break
        board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(h)]
        dust_location = []
        for i in range(h):
            for j in range(w):
                if board[i][j] == 'o':
                    r_x, r_y = i, j
                elif board[i][j] == '*':
                    dust_location.append((i, j))
        if not dust_location:
            print(0)
            continue

        res = sys.maxsize
        d = bfs(r_x, r_y)
        flag = False
        for i in range(h):
            for j in range(w):
                if board[i][j] == '*' and d[i][j] == 0:
                    print(-1)
                    flag = True
                    break
            if flag:
                break
        r2d = []
        for dust in dust_location:
            x, y = dust
            r2d.append(d[x][y])
        a = []
        idx = [i for i in range(len(dust_location))]
        for i in range(len(dust_location)):
            tmp_d = bfs(dust_location[i][0], dust_location[i][1])
            tmp = []
            for j in dust_location:
                xx, yy = j
                tmp.append(tmp_d[xx][yy])
            a.append(tmp)
        for p in permutations(idx):
            t = 0
            for i in range(len(p) - 1):
                t += a[p[i]][p[i+1]]
            t += r2d[p[0]]
            res = min(res, t)
        if not flag:
            print(res)
