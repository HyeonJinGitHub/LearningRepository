import sys
from collections import deque

def solution(x, y, d):
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    dist = [[[0] * 4 for _ in range(N)] for _ in range(M)]
    q = deque([(x, y, d)])

    while q:
        x, y, d = q.popleft()
        if x == d_x - 1 and y == d_y - 1 and d == d_d - 1:
            print(dist[x][y][d])
            return
        for i in range(1, 4):
            nx, ny = x + dx[d]*i, y + dy[d]*i
            if nx < 0 or nx >= M or ny < 0 or ny >= N: break
            if board[nx][ny] == 1: break
            if not dist[nx][ny][d]:
                dist[nx][ny][d] = dist[x][y][d] + 1
                q.append((nx, ny, d))
        for i in range(4):
            if i == d: continue
            if (i + d) % 4 == 1: k = 2
            else: k = 1
            if not dist[x][y][i]:
                dist[x][y][i] = dist[x][y][d] + k
                q.append((x, y, i))


if __name__ == '__main__':
    M, N = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(M)]
    s_x, s_y, s_d = map(int, sys.stdin.readline().split())
    d_x, d_y, d_d = map(int, sys.stdin.readline().split())
    solution(s_x - 1, s_y - 1, s_d - 1)