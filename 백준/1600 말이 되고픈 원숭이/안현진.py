import sys
from collections import deque
def bfs(x, y, z):
    dx1 = [0, 1, 0, -1]
    dy1 = [1, 0, -1, 0]
    dx2 = [-2, -2, -1, 1, 2, 2, 1, -1]
    dy2 = [-1, 1, 2, 2, 1, -1, -2, -2]

    dist = [[[0]*(K + 1) for _ in range(W)] for _ in range(H)]
    q = deque()
    q.append((x, y, z))
    while q:
        x, y, z = q.popleft()
        if x == H -1 and y == W - 1:
            print(dist)
            print(dist[x][y][z])
            return
        if z < K:
            for k in range(8):
                nx, ny = x + dx2[k], y + dy2[k]
                if 0 <= nx < H and 0 <= ny < W:
                    if a[nx][ny] == 0 and dist[nx][ny][z + 1] == 0:
                        dist[nx][ny][z + 1] = dist[x][y][z] + 1
                        q.append((nx, ny, z + 1))
        for k in range(4):
            nx, ny = x + dx1[k], y + dy1[k]
            if 0 <= nx < H and 0 <= ny < W:
                if a[nx][ny] == 0 and dist[nx][ny][z] == 0:
                    dist[nx][ny][z] = dist[x][y][z] + 1
                    q.append((nx, ny, z))
    print(-1)

if __name__ == '__main__':
    K = int(input())
    W, H = map(int, input().split())
    a = [[int(x) for x in sys.stdin.readline().split()] for y in range(H)]
    bfs(0, 0, 0)