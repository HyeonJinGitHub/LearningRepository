import sys
from collections import deque

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j][0], end=' ')
        print()
    print()

    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j][1], end=' ')
        print()
    print()

def bfs(x, y, z):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque()
    q.append((x, y, z))

    dist = [[[0] * (K + 1) for _ in range(M)] for _ in range(N)]
    dist[x][y][z] = 1

    while q:
        x, y, z = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if a[nx][ny] == 0 and dist[nx][ny][z] == 0:
                    dist[nx][ny][z] = dist[x][y][z] + 1
                    q.append((nx, ny, z))
                if z < K and a[nx][ny] == 1 and dist[nx][ny][z + 1] == 0:
                    dist[nx][ny][z + 1] = dist[x][y][z] + 1
                    q.append((nx, ny, z + 1))
    res = N*M + 1
    #print_arr(dist)
    for i in range(K + 1):
        if dist[N-1][M-1][i] != 0:
            res = min(res, dist[N-1][M-1][i])
    if res == N*M + 1:
        print(-1)
    else:
        print(res)
if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    a = [list(map(int, sys.stdin.readline().strip())) for _ in range(N)]
    bfs(0, 0, 0)