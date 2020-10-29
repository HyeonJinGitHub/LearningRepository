import sys
from collections import deque

def bfs(tomato):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    q = deque(tomato)
    res = 0
    while q:
        size = len(q)
        for _ in range(size):
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if board[nx][ny] == -1: continue
                    if board[nx][ny] == 0 and dist[nx][ny] == 0:
                        dist[nx][ny] = dist[x][y] + 1
                        q.append((nx, ny))
                        res = max(res, dist[nx][ny])
    return res

if __name__ == '__main__':
    M, N = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    dist = [[0] * M for _ in range(N)]
    tomato = []
    zero_count = 0
    for i in range(N):
        for j in range(M):
            if board[i][j] == 1:
                tomato.append((i, j))
            if board[i][j] == 0:
                zero_count += 1
    if zero_count == 0:
        print(0)
        exit(0)

    answer = bfs(tomato)
    for i in range(N):
        for j in range(M):
            if board[i][j] == 0 and dist[i][j] == 0:
                print(-1)
                exit(0)
    print(answer)