import sys
from collections import deque
import copy

def bfs():
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque()
    b = copy.deepcopy(a)
    for i in range(N):
        for j in range(M):
            if b[i][j] == 2:
                q.append((i, j))
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if b[nx][ny] == 0:
                    b[nx][ny] = 2
                    q.append((nx, ny))
    cur = 0
    for i in range(N):
        for j in range(M):
            if b[i][j] == 0:
                cur += 1
    return cur
if __name__== '__main__':
    N, M = map(int, sys.stdin.readline().split())
    a = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    res = 0
    for x1 in range(N):
        for y1 in range(M):
            if a[x1][y1] != 0: continue
            for x2 in range(N):
                for y2 in range(M):
                    if a[x2][y2] != 0: continue
                    for x3 in range(N):
                        for y3 in range(M):
                            if a[x3][y3] != 0: continue
                            if x1 == x2 and y1 == y2: continue
                            if x1 == x3 and y1 == y3: continue
                            if x2 == x3 and y2 == y3: continue
                            a[x1][y1] = 1
                            a[x2][y2] = 1
                            a[x3][y3] = 1
                            cur = bfs()
                            res = max(res, cur)
                            a[x1][y1] = 0
                            a[x2][y2] = 0
                            a[x3][y3] = 0
    print(res)