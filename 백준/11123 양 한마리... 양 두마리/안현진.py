from collections import deque
import sys

def bfs(x, y):
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    q = deque()
    check[x][y] = True
    q.append((x, y))

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < H and 0 <= ny < W:
                if not check[nx][ny] and a[nx][ny] == '#':
                    check[nx][ny] = True
                    q.append((nx, ny))

if __name__ == '__main__':
    T = int(input())
    for _ in range(T):
        H, W = map(int, input().split())
        a = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(H)]
        check = [[False for _ in range(W)] for _ in range(H)]
        res = 0
        for i in range(H):
            for j in range(W):
                if not check[i][j] and a[i][j] == '#':
                    bfs(i, j)
                    res += 1
        print(res)