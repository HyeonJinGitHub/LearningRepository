import sys
from collections import deque

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque([(x, y)])
    visited = [[False] * N for _ in range(N)]
    time = 0
    visited[x][y] = True
    shark_size = 2
    eat = 0
    flag = False
    result = 0
    while q:
        size = len(q)
        q = deque(sorted(q))
        for _ in range(size):
            x, y = q.popleft()

            if a[x][y] != 0 and a[x][y] < shark_size:
                a[x][y] = 0
                eat += 1

                if eat == shark_size:
                    shark_size += 1
                    eat = 0

                q = deque()
                visited = [[False] * N for _ in range(N)]
                visited[x][y] = True
                flag = True

                result = time
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < N:
                    if not visited[nx][ny] and a[nx][ny] <= shark_size:
                        q.append((nx, ny))
                        visited[nx][ny] = True
            if flag:
                flag = False
                break
        time += 1
    return result

if __name__ == '__main__':
    input = sys.stdin.readline
    N = int(input())
    a = [[int(x) for x in input().split()] for _ in range(N)]
    tmp = []
    init_size = 2
    x, y = 0, 0
    for i in range(len(a)):
        for j in range(len(a[i])):
            if a[i][j] == 9:
                  x, y = i, j
                  a[i][j] = 0
    print(bfs(x, y))


