from collections import deque
import sys

def bfs():
    for t in range(N):
        if a[0][t] == 0:
            dx = [0, 1, 0, -1]
            dy = [1, 0, -1, 0]

            q = deque()
            check[0][t] = 1
            q.append((0, t))
            while q:
                x, y = q.popleft()
                for k in range(4):
                    nx, ny = x + dx[k], y + dy[k]
                    if 0 <= nx < M and 0 <= ny < N:
                        if not check[nx][ny] and a[nx][ny] == 0:
                            if nx == M - 1:
                                return True
                            check[nx][ny] = 1
                            q.append((nx, ny))

    return False
if __name__ == '__main__':
    M, N = map(int, input().split())
    a = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(M)]
    check = [[0 for _ in range(N)] for _ in range(M)]
    res = bfs()
    if res:
        print('YES')
    else:
        print('NO')