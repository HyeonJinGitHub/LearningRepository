import sys
from collections import deque
def bfs(x, y):
    q = deque([(x, y)])
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    check[x][y] = True
    res = 0
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if a[nx][ny] == 'L' and dist[nx][ny] == 0 and not check[nx][ny]:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    check[nx][ny] = True
                    res = max(res, dist[nx][ny])
    return res
if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    a = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    result = 0
    for i in range(len(a)):
        for j in range(len(a[i])):
            if a[i][j] == 'L':
                dist = [[0] * M for _ in range(N)]
                check = [[False] * M for _ in range(N)]
                tmp = bfs(i, j)
                if tmp > result:
                    result = tmp
    print(result)