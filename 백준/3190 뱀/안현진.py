import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, -1, 0, 1]

def solution(x, y):
    board[x][y] = 1
    d = 3
    q = deque([(x, y)])
    time = 0
    cmd_idx = 0
    while q:
        x, y = q.pop()
        if cmd_idx < len(command):
            t, c = command[cmd_idx]
            if time == t:
                if c == 'D':
                    d = (d + 1) % 4
                else:
                    d = (d - 1) % 4
                cmd_idx += 1
        nx, ny = x + dx[d], y + dy[d]
        if 0 <= nx < N and 0 <= ny < N:
            if board[nx][ny] == 1:
                return time + 1
            elif board[nx][ny] == 2:
                q.append((x, y))
                q.append((nx, ny))
                board[nx][ny] = 1
            else:
                if q:
                    tx, ty = q.popleft()
                    board[tx][ty] = 0
                    q.append((x, y))
                else:
                    board[x][y] = 0
                q.append((nx, ny))
                board[nx][ny] = 1
        else: return time + 1
        time += 1

if __name__ == '__main__':
    N = int(input())
    board = [[0] * N for _ in range(N)]
    K = int(input())
    for _ in range(K):
        r, c = map(int, sys.stdin.readline().split())
        board[r - 1][c - 1] = 2
    L = int(input())
    command = []
    for _ in range(L):
        a, b = map(str, sys.stdin.readline().split())
        command.append((int(a), b))
    print(solution(0, 0))
