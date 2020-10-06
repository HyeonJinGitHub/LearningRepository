import sys
from collections import deque

def bfs(x, y, g):
    q = deque([(x, y)])
    q1 = deque([(x, y)])
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    dist[x][y] = 1
    group[x][y] = g
    cnt = 1
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if a[nx][ny] == 1: continue
                if a[nx][ny] == 0 and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    q1.append((nx, ny))
                    cnt += 1
                    group[nx][ny] = g

    while q1:
        x, y = q1.popleft()
        dist[x][y] = cnt

def break_block():
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    for i in range(N):
        for j in range(M):
            if a[i][j] == 1:
                g_c = []
                for k in range(4):
                    ni, nj = i + dx[k], j + dy[k]
                    if 0 <= ni < N and 0 <= nj < M:
                        if a[ni][nj] == 0 and group[ni][nj] not in g_c:
                            block_dist[i][j] += dist[ni][nj]
                            g_c.append(group[ni][nj])

if __name__ =='__main__':
    N, M = map(int, sys.stdin.readline().split())
    dist = [[0] * M for _ in range(N)]
    block_dist = [[0] * M for _ in range(N)]
    a = [[] for _ in range(N)]
    group = [[0] * M for _ in range(N)]
    g = 1
    for i in range(N):
        a[i] = list(map(int, sys.stdin.readline().rstrip()))
        for j in range(M):
            if a[i][j] == 1:
                block_dist[i][j] = 1
    for i in range(N):
        for j in range(M):
            if dist[i][j] == 0 and a[i][j] == 0:
                bfs(i, j, g)
                g += 1
    break_block()
    for i in range(N):
        for j in range(M):
            print(str(block_dist[i][j] % 10), end='')
        print()
