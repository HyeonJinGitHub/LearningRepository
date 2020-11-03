import sys
from itertools import combinations
from collections import deque

def bfs(chicken):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque(chicken)

    for d in chicken:
        a, b = d
        dist[a][b] = 0
        tmp_board[a][b] = 2
    while q:
        size = len(q)
        for _ in range(size):
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < N:
                    if tmp_board[nx][ny] != 2 and dist[nx][ny] == -1:
                        dist[nx][ny] = dist[x][y] + 1
                        q.append((nx, ny))
    res = 0
    for i in range(N):
        for j in range(N):
            if tmp_board[i][j] == 1:
                res += dist[i][j]
    for d in chicken:
        a, b = d
        tmp_board[a][b] = 0
    return res
if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    chicken = []
    ans = sys.maxsize
    for i in range(N):
        for j in range(N):
            if board[i][j] == 2:
                chicken.append((i, j))
    tmp_board = [[0] * N for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1:
                tmp_board[i][j] = 1
    for y in combinations(chicken, M):
        dist = [[-1] * N for _ in range(N)]
        ans = min(ans, bfs(list(y)))
    print(ans)
