import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(x, y, flag):
    q = deque([(x, y)])
    if not flag: dist[x][y] = 0
    else: dist[x][y] = sword

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if board[nx][ny] == 1: continue
                if not board[nx][ny] and dist[nx][ny] == -1:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    return dist[N - 1][M - 1]

if __name__ == '__main__':
    N, M, T = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    barrier = []
    dist = [[-1] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if board[i][j] == 1:
                barrier.append((i, j))
            if board[i][j] == 2:
                sx, sy = i, j
                board[i][j] = 0
    tmp1 = bfs(0, 0, False)

    if dist[sx][sy] == -1:
        if tmp1 == -1 or tmp1 > T: print('Fail')
        else: print(tmp1)
    else:
        sword = dist[sx][sy]
        dist = [[-1] * M for _ in range(N)]
        for (bx, by) in barrier:
            board[bx][by] = 0
        tmp2 = bfs(sx, sy, True)
        ans = sys.maxsize
        if tmp1 != -1:
            ans = min(tmp1, tmp2)
            if ans <= T: print(ans)
            else: print('Fail')
        else:
            if tmp2 <= T: print(tmp2)
            else: print('Fail')
