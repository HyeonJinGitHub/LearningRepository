import sys
from collections import deque

def move(x, y, dx, dy):
    global ans
    total = 0
    for k in range(9):
        nx, ny = x + dx[k], y + dy[k]
        if k == 0:
            if 0 <= nx < N and 0 <= ny < N:
                board[nx][ny] += int(board[x][y] * 0.05)
            else:
                ans += int(board[x][y] * 0.05)
            total += int(board[x][y] * 0.05)
        elif k == 1 or k == 5:
            if 0 <= nx < N and 0 <= ny < N:
                board[nx][ny] += int(board[x][y] * 0.1)
            else:
                ans += int(board[x][y] * 0.1)
            total += int(board[x][y] * 0.1)
        elif k == 2 or k == 6:
            if 0 <= nx < N and 0 <= ny < N:
                board[nx][ny] += int(board[x][y] * 0.07)
            else:
                ans += int(board[x][y] * 0.07)
            total += int(board[x][y] * 0.07)
        elif k == 3 or k == 7:
            if 0 <= nx < N and 0 <= ny < N:
                board[nx][ny] += int(board[x][y] * 0.01)
            else:
                ans += int(board[x][y] * 0.01)
            total += int(board[x][y] * 0.01)
        elif k == 4 or k == 8:
            if 0 <= nx < N and 0 <= ny < N:
                board[nx][ny] += int(board[x][y] * 0.02)
            else:
                ans += int(board[x][y] * 0.02)
            total += int(board[x][y] * 0.02)
    return total

if __name__ == '__main__':
    N = int(input())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    tmp_board = [[0] * N for _ in range(N)]
    q = deque()
    flag = 0
    ans = 0
    cnt = 1
    i, j = 0, 0
    while True:
        if flag == 0:
            q.appendleft((i, j, (flag + 2) % 4))
            tmp_board[i][j] = cnt
            j += 1
            cnt += 1
            if j == N or tmp_board[i][j] != 0:
                q.popleft()
                q.appendleft((i, j - 1, (flag + 3) % 4))
                j -= 1
                i += 1
                flag = 1
        if flag == 1:
            q.appendleft((i, j, (flag + 2) % 4))
            tmp_board[i][j] = cnt
            cnt += 1
            i += 1
            if i == N or tmp_board[i][j] != 0:
                q.popleft()
                q.appendleft((i - 1, j, (flag + 3) % 4))
                i -= 1
                j -= 1
                flag = 2
        if flag == 2:
            q.appendleft((i, j, (flag + 2) % 4))
            tmp_board[i][j] = cnt
            cnt += 1
            j -= 1
            if j == -1 or tmp_board[i][j] != 0:
                q.popleft()
                q.appendleft((i, j + 1, (flag + 3) % 4))
                j += 1
                i -= 1
                flag = 3
        if flag == 3:
            q.appendleft((i, j, (flag + 2) % 4))
            tmp_board[i][j] = cnt
            cnt += 1
            i -= 1
            if i == -1 or tmp_board[i][j] != 0:
                q.popleft()
                q.appendleft((i + 1, j, (flag + 3) % 4))
                j += 1
                i += 1
                flag = 0
        if cnt == N*N:
            q.appendleft((i, j, (flag + 2) % 4))
            tmp_board[i][j] = cnt
            break
    while q:
        x, y, d = q.popleft()
        if board[x][y] == 0: continue
        if d == 2:
            dx = [0, -1, -1, -1, -2, 1, 1, 1, 2]
            dy = [-2, -1, 0, 1, 0, -1, 0, 1, 0]
            tmp = move(x, y, dx, dy)
            nnx, nny = x, y - 1
            if 0 <= nnx < N and 0 <= nny < N:
                board[nnx][nny] += (board[x][y] - tmp)
            else:
                ans += (board[x][y] - tmp)
        elif d == 0:
            dx = [0, -1, -1, -1, -2, 1, 1, 1, 2]
            dy = [2, 1, 0, -1, 0, 1, 0, -1, 0]
            tmp = move(x, y, dx, dy)
            nnx, nny = x, y + 1
            if 0 <= nnx < N and 0 <= nny < N:
                board[nnx][nny] += (board[x][y] - tmp)
            else:
                ans += (board[x][y] - tmp)
        elif d == 1:
            dx = [2, 1, 0, -1, 0, 1, 0, -1, 0]
            dy = [0, -1, -1, -1, -2, 1, 1, 1, 2]
            tmp = move(x, y, dx, dy)
            nnx, nny = x + 1, y
            if 0 <= nnx < N and 0 <= nny < N:
                board[nnx][nny] += (board[x][y] - tmp)
            else:
                ans += (board[x][y] - tmp)
        elif d == 3:
            dx = [-2, -1, 0, 1, 0, -1, 0, 1, 0]
            dy = [0, -1, -1, -1, -2, 1, 1, 1, 2]
            tmp = move(x, y, dx, dy)
            nnx, nny = x - 1, y
            if 0 <= nnx < N and 0 <= nny < N:
                board[nnx][nny] += (board[x][y] - tmp)
            else:
                ans += (board[x][y] - tmp)
        board[x][y] = 0
    print(ans)