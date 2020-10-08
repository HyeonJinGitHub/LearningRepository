import sys
from collections import deque

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()
def check_bfs(check, x, y, group, g_count):
    q = deque([(x, y)])
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    check[x][y] = True
    group[x][y] = g_count

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if not check[nx][ny] and a[nx][ny] != 0:
                    q.append((nx, ny))
                    check[nx][ny] = True
                    group[nx][ny] = g_count

def bfs(bingsan):
    q = deque(bingsan)
    q1 = deque()
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    cnt = 0
    while q:
        size = len(q)
        cnt += 1
        for _ in range(size):
            x, y = q.popleft()
            zero_count = 0
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if a[nx][ny] == 0:
                        zero_count += 1
            q1.append((x, y, zero_count))
        while q1:
            tmp_x, tmp_y, d = q1.popleft()
            a[tmp_x][tmp_y] -= d
            if a[tmp_x][tmp_y] <= 0: a[tmp_x][tmp_y] = 0
            else:
                q.append((tmp_x, tmp_y))
        check = [[False] * M for _ in range(N)]
        group = [[-1] * M for _ in range(N)]
        g_count = 0
        #print_arr(a)
        for i in range(N):
            for j in range(M):
                if a[i][j] != 0 and not check[i][j]:
                    check_bfs(check, i, j, group, g_count)
                    g_count += 1
        for i in range(N):
            for j in range(M):
                if group[i][j] != -1 and group[i][j] != g_count - 1:
                    print(cnt)
                    return
    print(0)

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    a = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    bingsan = []
    for i in range(N):
        for j in range(M):
            if a[i][j] != 0:
                bingsan.append((i, j))
    bfs(bingsan)