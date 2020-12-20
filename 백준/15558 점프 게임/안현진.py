import sys
from collections import deque

def bfs(x, y, K):
    check = [[False] * N for _ in range(2)]
    dx = [0, 0, 1, -1]
    dy = [1, -1, K, K]
    q = deque([(x, y)])
    check[x][y] = True
    time = 0
    while q:
        size = len(q)
        for _ in range(size):
            x, y = q.popleft()
            if board[x][y] == -1: continue
            if y >= N:
                print(1)
                exit(0)
            if time >= N:
                print(0)
                exit(0)
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < 2 and ny >= N:
                    print(1)
                    exit(0)
                if 0 <= nx < 2 and 0 <= ny < N:
                    if board[nx][ny] == 0 or board[nx][ny] == -1: continue
                    if not check[nx][ny]:
                        check[nx][ny] = True
                        q.append((nx, ny))
        board[0][time] = -1
        board[1][time] = -1
        time += 1
    print(0)

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(2)]
    bfs(0, 0, K)