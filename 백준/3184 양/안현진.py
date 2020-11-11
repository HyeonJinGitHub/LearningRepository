import sys
from collections import deque

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque([(x, y)])
    group[x][y] = group_count
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < R and 0 <= ny < C:
                if board[nx][ny] == '#': continue
                if group[nx][ny] == 0:
                    group[nx][ny] = group_count
                    q.append((nx, ny))

if __name__ == '__main__':
    R, C = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
    yang, wolf = 0, 0
    group = [[0] * C for _ in range(R)]
    group_count = 1
    for i in range(R):
        for j in range(C):
            if board[i][j] == 'o' and group[i][j] == 0:
                bfs(i, j)
                group_count += 1
    for i in range(R):
        for j in range(C):
            if board[i][j] == 'v' and group[i][j] == 0:
                wolf += 1
    if group_count != 1:
        for i in range(1, group_count):
            tmp_yang, tmp_wolf = 0, 0
            for x in range(R*C):
                r, c = x // C, x % C
                if group[r][c] != i: continue
                if board[r][c] == 'o':
                    tmp_yang += 1
                elif board[r][c] == 'v':
                    tmp_wolf += 1
            if tmp_yang > tmp_wolf:
                yang += tmp_yang
            else:
                wolf += tmp_wolf
    print(yang, wolf)