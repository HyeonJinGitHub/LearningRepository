from collections import deque
import sys

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j][0], end=' ')
        print()
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j][1], end=' ')
        print()
def bfs(x, y, z):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque()
    q.append((x, y, z))
    dist = [[[0] * 2 for _ in range(M)] for _ in range(N)]
    dist[x][y][z] = 1

    while q:
        x, y, z = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if a[nx][ny] == 0 and dist[nx][ny][z] == 0:
                    dist[nx][ny][z] = dist[x][y][z] + 1
                    q.append((nx, ny, z))
                if z == 0 and a[nx][ny] == 1 and dist[nx][ny][z + 1] == 0:
                    q.append((nx, ny, z + 1))
                    dist[nx][ny][z + 1] = dist[x][y][z] + 1
    # print_arr(dist)
    res1 = dist[N-1][M-1][0]
    res2 = dist[N-1][M-1][1]

    if res1 != 0 and res2 != 0:
        print(min(res1, res2))
    elif res1 == 0 and res2 != 0:
        print(res2)
    elif res1 != 0 and res2 == 0:
        print(res1)
    else:
        print(-1)
if __name__ == '__main__':
    N, M = map(int, input().split())
    a = [[int(x) for x in sys.stdin.readline().strip()] for _ in range(N)]
    bfs(0, 0, 0)