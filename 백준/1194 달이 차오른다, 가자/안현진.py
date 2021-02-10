import sys
from collections import deque

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

def haveKey(cur_key, door):
    value = cur_key & (1 << (ord(door) - ord('A')))
    return True if value else False

def bfs(x, y):
    q = deque([(x, y, 0, 0)])
    check = [[[False] * (1 << 6) for _ in range(50)] for _ in range(50)]
    check[x][y][0] = True

    while q:
        x, y, cnt, key = q.popleft()
        if board[x][y] == '1': return cnt
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if not check[nx][ny][key]:
                    if board[nx][ny] == '1' or board[nx][ny] == '.':
                        check[nx][ny][key] = True
                        q.append((nx, ny, cnt + 1, key))
                    elif 'a' <= board[nx][ny] <= 'f':
                        tmp_key = key | (1 << (ord(board[nx][ny]) - ord('a')))
                        check[nx][ny][tmp_key] = True
                        q.append((nx, ny, cnt + 1, tmp_key))
                    elif 'A' <= board[nx][ny] <= 'Z':
                        if haveKey(key, board[nx][ny]):
                            check[nx][ny][key] = True
                            q.append((nx, ny, cnt + 1, key))
    return -1

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if board[i][j] == '0':
                sx, sy = i, j
                board[i][j] = '.'
    print(bfs(sx, sy))