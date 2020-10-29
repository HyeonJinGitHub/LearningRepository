import sys
from collections import deque

def bfs(x, y):
    dx = [-1, -1, -1, 0, 1, 1, 1, 0]
    dy = [-1, 0, 1, 1, 1, 0, -1, -1]

    q = deque([(x, y)])

    while q:
        x, y = q.popleft()
        for k in range(8):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < h and 0 <= ny < w:
                if board[nx][ny] == 1 and group[nx][ny] == 0:
                    group[nx][ny] = group_count
                    q.append((nx, ny))

if __name__ == '__main__':
    while True:
        w, h = map(int, sys.stdin.readline().split())
        if w == 0 and h == 0: break
        board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(h)]
        group = [[0] * w for _ in range(h)]
        group_count = 1
        for i in range(h):
            for j in range(w):
                if board[i][j] == 1 and group[i][j] == 0:
                    bfs(i, j)
                    group_count += 1
        if group_count == 1:
            print(0)
            continue
        print(group_count - 1)

