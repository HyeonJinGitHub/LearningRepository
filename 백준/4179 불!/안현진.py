import sys
from collections import deque

def bfs_j(x, y):
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < R + 2 and 0 <= ny < C + 2:
                if a[nx][ny] == '.' and j_dist[nx][ny] == 0 and (j_dist[x][y] + 1 < f_dist[nx][ny] or f_dist[nx][ny] == 0 or nx == 0 or nx == R + 1 or ny == 0 or ny == C + 1):

                    j_dist[nx][ny] = j_dist[x][y] + 1
                    q.append((nx, ny))
def bfs_f(x, y):
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    q = deque()
    q.append((x, y))

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 1 <= nx < R + 1 and 1 <= ny < C + 1:
                if (a[nx][ny] == '.' or a[nx][ny] == 'J') and f_dist[nx][ny] == 0 or f_dist[nx][ny] > f_dist[x][y] + 1:
                    f_dist[nx][ny] = f_dist[x][y] + 1
                    q.append((nx, ny))

if __name__ == '__main__':
    R, C = map(int, input().split())
    MAX = 1100000
    a = [['.'] * (C + 2)] + [['.'] + list(map(str, sys.stdin.readline().strip())) + ['.'] for _ in range(R)] + [['.'] * (C + 2)]
    j_dist = [[0] * (C + 2) for _ in range(R + 2)]
    f_dist = [[0] * (C + 2) for _ in range(R + 2)]
    for i in range(len(a)):
        for j in range(len(a[i])):
            if a[i][j] == 'F':
                bfs_f(i, j)
    for i in range(len(a)):
        ch = False
        for j in range(len(a[i])):
            if a[i][j] == 'J':
                bfs_j(i, j)
                ch = True
                break
        if ch:
            break
    tmp1 = MAX
    tmp2 = -100000
    for i in range(len(f_dist)):
        for j in range(len(f_dist[i])):
            tmp2 = max(tmp2, f_dist[i][j])
    for i in range(C + 2):
        if j_dist[0][i] != 0 and j_dist[0][i] < tmp1:
            tmp1 = j_dist[0][i]
    for i in range(R + 2):
        if j_dist[i][0] != 0 and j_dist[i][0] < tmp1:
            tmp1 = j_dist[i][0]
    for i in range(C + 2):
        if j_dist[R + 1][i] != 0 and j_dist[R + 1][i] < tmp1:
            tmp1 = j_dist[R + 1][i]
    for i in range(R + 2):
        if j_dist[i][C + 1] != 0 and j_dist[i][C + 1] < tmp1:
            tmp1 = j_dist[i][C + 1]
    if tmp1 == MAX or tmp2 < tmp1 and tmp2 != 0:
        print('IMPOSSIBLE')
    else:
        print(tmp1)