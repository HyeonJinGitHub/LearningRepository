from itertools import product
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def move_down(move_board):
    for j in range(W):
        idx = H - 1
        x = 0
        for i in range(H - 1, -1, -1):
            if not move_board[i][j]: continue
            if not x:
                x = move_board[i][j]
            else:
                move_board[idx][j] = x
                x = move_board[i][j]
                idx -= 1
            move_board[i][j] = 0
        if x != 0: move_board[idx][j] = x
    return move_board

def solution(n):
    global tmp_board
    q = deque()
    for i in range(H):
        if tmp_board[i][n]:
            q.append((i, n, tmp_board[i][n]))
            break
    while q:
        size = len(q)
        for _ in range(size):
            x, y, d = q.popleft()
            d -= 1
            tmp_board[x][y] = 0
            if not d: continue
            for k in range(4):
                nx, ny = x, y
                for _ in range(d):
                    nnx, nny = nx + dx[k], ny + dy[k]
                    if 0 <= nnx < H and 0 <= nny < W:
                        if tmp_board[nnx][nny] and (nnx, nny, tmp_board[nnx][nny]) not in q:
                            q.append((nnx, nny, tmp_board[nnx][nny]))
                        nx, ny = nnx, nny
    tmp_board = move_down(tmp_board)

if __name__ == '__main__':
    for tc in range(int(input())):
        N, W, H = map(int, input().split())
        board = [list(map(int, input().rstrip().split())) for _ in range(H)]
        ans = W * H + 1
        selected = [i for i in range(W)]
        for selected_number in product(selected, repeat=N):
            tmp_board = [x[:] for x in board]
            for n in selected_number:
                solution(n)
            tmp = 0
            for i in range(H):
                for j in range(W):
                    if tmp_board[i][j]:
                        tmp += 1
            if not tmp:
                ans = 0
                break
            ans = min(ans, tmp)
        print('#{0} {1}'.format(tc + 1, ans))
