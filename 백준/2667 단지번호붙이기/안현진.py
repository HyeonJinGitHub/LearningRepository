import sys
from collections import deque

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0 ,-1]
    q = deque([(x, y)])
    group[x][y] = group_count
    cnt = 1
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx <N and 0 <= ny < N:
                if board[nx][ny] == 1 and group[nx][ny] == 0:
                    group[nx][ny] = group_count
                    q.append((nx, ny))
                    cnt += 1
    return cnt

if __name__ == '__main__':
    N = int(input())
    board = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(N)]
    group = [[0] * N for _ in range(N)]
    group_count = 1
    res = []
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1 and group[i][j] == 0:
                res.append(bfs(i, j))
                group_count += 1
    if group_count == 1:
        print(0)
        exit(0)
    res.sort()
    print(group_count - 1)
    for y in res:
        print(y)

