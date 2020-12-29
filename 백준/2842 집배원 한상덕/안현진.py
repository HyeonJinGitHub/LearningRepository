import sys
from collections import deque

dx = [1, 0, -1, 0, -1, 1, 1, -1]
dy = [0, 1, 0, -1, 1, 1, -1, -1]

if __name__ == '__main__':
    N = int(input())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    tiredness = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    houses = 0
    fatigue = []
    for i in range(N):
        for j in range(N):
            if board[i][j] == 'P':
                sx, sy = i, j
            if board[i][j] == 'K':
                houses += 1
            fatigue.append(tiredness[i][j])
    fatigue = sorted(set(fatigue))
    left, right = 0, 0
    ans = sys.maxsize
    while left < len(fatigue):
        visit = [[False] * N for _ in range(N)]
        tired = tiredness[sx][sy]
        q = deque()
        K = 0
        if fatigue[left] <= tired <= fatigue[right]:
            visit[sx][sy] = True
            q.append((sx, sy))
        while q:
            x, y = q.popleft()
            for k in range(8):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < N:
                    if visit[nx][ny]: continue
                    tired = tiredness[nx][ny]
                    if fatigue[left] <= tired <= fatigue[right]:
                        visit[nx][ny] = True
                        q.append((nx, ny))
                        if board[nx][ny] == 'K': K += 1
        if K == houses:
            ans = min(ans, fatigue[right] - fatigue[left])
            left += 1
        elif right + 1 < len(fatigue):
            right += 1
        else: break
    print(ans)