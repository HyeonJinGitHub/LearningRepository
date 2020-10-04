import sys
from collections import deque

def bfs(x, y):
    global F
    q = deque([(x, y)])
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    check = [[False] * W for _ in range(H)]
    check[x][y] = True

    while q:
        size = len(q)
        F -= 1
        for _ in range(size):
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < H and 0 <= ny < W:
                    if F <= 0:
                        print('인성 문제있어??')
                        return
                    if not check[nx][ny] and a[nx][ny] == 0:
                        check[nx][ny] = True
                        q.append((nx, ny))
                    if not check[nx][ny] and a[nx][ny] == 1:
                        if height[nx][ny] < height[x][y]:
                            check[nx][ny] = True
                            q.append((nx, ny))
                        else:
                            if F + 1 >= abs(height[nx][ny] - height[x][y]):
                                check[nx][ny] = True
                                q.append((nx, ny))
                    if nx == x_d - 1 and ny == y_d - 1:
                        if F > 0 and check[nx][ny]:
                            print('잘했어!!')
                            return
    print('인성 문제있어??')

if __name__ == '__main__':
    for _ in range(int(input())):
        H, W, O, F, x_s, y_s, x_d, y_d = map(int, sys.stdin.readline().split())
        a = [[0] * W for _ in range(H)]
        height = [[0] * W for _ in range(H)]
        for i in range(O):
            x1, y1, h = map(int, sys.stdin.readline().split())
            a[x1 - 1][y1 - 1] = 1
            height[x1 - 1][y1 - 1] = h
        bfs(x_s - 1, y_s - 1)