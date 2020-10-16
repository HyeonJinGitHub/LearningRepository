import sys
from itertools import combinations
from collections import deque

def print_arr(arr):
    for tx in range(len(arr)):
        for ty in range(len(arr[tx])):
            print(arr[tx][ty], end=' ')
        print()
    print()
def solution(chicken):
    dist = [[0] * N for _ in range(N)]
    check = [[False] * N for _ in range(N)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque()
    d = 0
    for i in chicken:
        a, b = i
        q.append(i)
        check[a][b] = True

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if not check[nx][ny] and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    check[nx][ny] = True
                    q.append((nx, ny))
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1:
                d += dist[i][j]
    return d

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    chicken = []
    res = sys.maxsize
    for i in range(N):
        for j in range(N):
            if board[i][j] == 2:
                chicken.append((i, j))
    for y in combinations(chicken, M):
        res = min(res, solution(y))

    print(res)