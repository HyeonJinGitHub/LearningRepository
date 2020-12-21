import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def bfs(x, y):
    q = deque([(x, y)])
    cnt = 1
    group[x][y] = group_number
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if board[nx][ny] and group[nx][ny] == -1:
                    cnt += 1
                    group[nx][ny] = group_number
                    q.append((nx, ny))
    return cnt

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]

    group = [[-1] * M for _ in range(N)]
    group_number = 0
    width = []
    for i in range(N):
        for j in range(M):
            if board[i][j] and group[i][j] == -1:
                width.append(bfs(i, j))
                group_number += 1
    check = [False] * group_number
    ans = 0
    for i in range(N):
        for j in range(M):
            if board[i][j]: continue
            tmp = 1
            visited_group = []
            for k in range(4):
                ni, nj = i + dx[k], j + dy[k]
                if 0 <= ni < N and 0 <= nj < M:
                    if board[ni][nj] and not check[group[ni][nj]]:
                        check[group[ni][nj]] = True
                        tmp += width[group[ni][nj]]
                        visited_group.append(group[ni][nj])
            ans = max(ans, tmp)
            for y in visited_group:
                check[y] = False
    print(ans)