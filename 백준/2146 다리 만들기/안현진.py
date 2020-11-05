import sys
from collections import deque

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()

def bfs(x, y, group_count):
    q = deque([(x, y)])
    check = [[False] * N for _ in range(N)]
    check[x][y] = True
    group[x][y] = group_count
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if not check[nx][ny] and board[nx][ny] == 1:
                    q.append((nx, ny))
                    check[nx][ny] = True
                    group[nx][ny] = group_count
def group_bfs(group_location, group_n):
    q = deque(group_location)
    dist = [[0] * N for _ in range(N)]
    res = sys.maxsize

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if group[nx][ny] == group_n: continue
                if board[nx][ny] == 0 or group[nx][ny] != group_n :
                    if dist[nx][ny] == 0:
                        dist[nx][ny] = dist[x][y] + 1
                        q.append((nx, ny))
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1 and dist[i][j] == 2:
                print(1)
                exit(0)
            if board[i][j] == 1 and group[i][j] != group_n:
                res = min(res, dist[i][j])
    return res -1

if __name__ == '__main__':
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    N = int(input())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    group = [[0] * N for _ in range(N)]
    group_count = 1
    ans = sys.maxsize
    for i in range(N):
        for j in range(N):
            if board[i][j] == 1 and group[i][j] == 0:
                bfs(i, j, group_count)
                group_count += 1
    for i in range(1, group_count):
        group_location = []
        for j in range(N*N):
            r, c = j // N, j % N
            if group[r][c] == i:
                group_location.append((r, c))
        ans = min(ans, group_bfs(group_location, i))
    print(ans)
