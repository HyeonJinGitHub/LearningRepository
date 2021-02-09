import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y):
    q = deque([(x, y)])
    group[x][y] = group_cnt
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if group[nx][ny] == -1 and check[nx][ny]:
                    q.append((nx, ny))
                    group[nx][ny] = group_cnt

if __name__ == '__main__':
    N = int(input())
    arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    ans = 1
    max_height, now_height = 0, 1
    for i in range(N):
        for j in range(N):
            max_height = max(max_height, arr[i][j])

    while now_height <= max_height:
        check = [[True] * N for _ in range(N)]
        for i in range(N):
            for j in range(N):
                if arr[i][j] <= now_height:
                    check[i][j] = False
        group = [[-1] * N for _ in range(N)]
        group_cnt = 0
        for i in range(N):
            for j in range(N):
                if check[i][j] and group[i][j] == -1:
                    bfs(i, j)
                    group_cnt += 1
        ans = max(ans, group_cnt)
        now_height += 1
    print(ans)