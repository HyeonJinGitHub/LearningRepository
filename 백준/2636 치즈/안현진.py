import sys
from collections import deque

def bfs():
    q = deque()
    remove_q = deque()
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    arr = []
    for i in range(N):
        a[i][0] = -1
        q.append((i, 0))
    for i in range(1, M):
        a[0][i] = -1
        q.append((0, i))
    for i in range(1, N):
        a[i][M-1] = -1
        q.append((i, M-1))
    for i in range(1, M - 1):
        a[N - 1][i] = -1
        q.append((N-1, i))
    for i in range(1, 101):
        while q:
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if a[nx][ny] == 1:
                        a[nx][ny] = -1
                        remove_q.append((nx, ny))
                    elif a[nx][ny] == 0:
                        a[nx][ny] = -1
                        q.append((nx, ny))

        res = len(remove_q)
        while remove_q:
            x, y, = remove_q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if a[nx][ny] == 1:
                        a[nx][ny] = -1
                        arr.append((nx, ny))
                    elif a[nx][ny] == 0:
                        a[nx][ny] = -1
                        q.append((nx, ny))
        if not remove_q and not arr:
            print(i)
            print(res)
            return
        while arr:
            remove_q.append(arr.pop(0))

if __name__ == '__main__':
    N, M = map(int, input().split())
    a = [[int(x) for x in sys.stdin.readline().split()] for y in range(N)]
    bfs()