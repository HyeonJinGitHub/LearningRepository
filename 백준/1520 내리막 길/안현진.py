import sys

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()
def dfs(x, y):
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    if x == M-1 and y == N-1:
        return 1
    if dist[x][y] != -1:
        return dist[x][y]
    dist[x][y] = 0
    for k in range(4):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < M and 0 <= ny < N:
            if a[nx][ny] < a[x][y]:
                dist[x][y] += dfs(nx, ny)
    return dist[x][y]
if __name__== '__main__':
    M, N = map(int, input().split())
    a = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
    dist = [[-1] * N for _ in range(M)]
    print(dfs(0, 0))
    #print_arr(dist)