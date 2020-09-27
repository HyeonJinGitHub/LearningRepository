from collections import deque

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()

if __name__ == '__main__':
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    N, M = map(int, input().split())
    n = int(input())
    a = [[0] * (N + 1) for _ in range(M + 1)]
    dist = [[0] * (N + 1) for _ in range(M + 1)]
    t_d = 0
    for i in range(n + 1):
        dir, x = map(int, input().split())
        if i != n:
            if dir == 1: a[0][x] = 1
            elif dir == 2: a[M][x] = 1
            elif dir == 3: a[x][0] = 1
            elif dir == 4: a[x][N]  = 1
        else:
            if dir == 1:
                t_d = 0
                a[0][x] = 2
            elif dir == 2:
                t_d = 2
                a[M][x] = 2
            elif dir == 3:
                t_d = 3
                a[x][0] = 2
            elif dir == 4:
                t_d = 1
                a[x][N] = 2
    q = deque()
    for i in range(len(a)):
        ch = False
        for j in range(len(a[i])):
            if a[i][j] == 2:
                q.append((i, j))
                ch = True
                break
        if ch: break
    res = 0
    while q:
        x, y = q.popleft()
        if t_d == 0:
            nx, ny = x + dx[t_d], y + dy[t_d]
            if 0 <= nx < M + 1 and 0 <= ny < N + 1:
                if (a[nx][ny] == 0 or a[nx][ny] == 1) and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    if nx == 0 and ny == N:
                        t_d = 1
                    if a[nx][ny] == 1:
                        res += min(dist[nx][ny], 2*(N+M) - dist[nx][ny])
        elif t_d == 1:
            nx, ny = x + dx[t_d], y + dy[t_d]
            if 0 <= nx < M + 1 and 0 <= ny < N + 1:
                if (a[nx][ny] == 0 or a[nx][ny] == 1) and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    if nx == M and ny == N:
                        t_d = 2
                    if a[nx][ny] == 1:
                        res += min(dist[nx][ny], 2*(N+M) - dist[nx][ny])
        elif t_d == 2:
            nx, ny = x + dx[t_d], y + dy[t_d]
            if 0 <= nx < M + 1 and 0 <= ny < N + 1:
                if (a[nx][ny] == 0 or a[nx][ny] == 1) and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    if nx == M and ny == 0:
                        t_d = 3
                    if a[nx][ny] == 1:
                        res += min(dist[nx][ny], 2*(N+M) - dist[nx][ny])
        elif t_d == 3:
            nx, ny = x + dx[t_d], y + dy[t_d]
            if 0 <= nx < M + 1 and 0 <= ny < N + 1:
                if (a[nx][ny] == 0 or a[nx][ny] == 1) and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    if nx == 0 and ny == 0:
                        t_d = 0
                    if a[nx][ny] == 1:
                        res += min(dist[nx][ny], 2*(N+M) - dist[nx][ny])
    # print_arr(a)
    # print()
    # print_arr(dist)
    print(res)