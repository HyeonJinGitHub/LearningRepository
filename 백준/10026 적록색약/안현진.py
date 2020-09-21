from collections import deque
import sys

def bfs(x, y, color):
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    q = deque()
    q.append((x, y))
    check1[x][y] = True
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if not check1[nx][ny] and a[nx][ny] == color:
                    check1[nx][ny] = True
                    q.append((nx, ny))
if __name__ == '__main__':
    N = int(input())
    a = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    check1 = [[False for _ in range(N)] for _ in range(N)]
    cnt1 = 0
    for i in range(N):
        for j in range(N):
            if not check1[i][j]:
                bfs(i, j, a[i][j])
                cnt1 += 1
    cnt2 = 0
    check1 = [[False for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if a[i][j] == 'R':
                a[i][j] = 'G'
    for i in range(N):
        for j in range(N):
            if not check1[i][j]:
                bfs(i, j, a[i][j])
                cnt2 += 1
    print(cnt1, cnt2)