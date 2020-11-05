import sys

def bfs(x, y):
    mx = 0
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    q = set([(x, y, board[x][y])])

    while q:
        flag = True
        x, y, ans = q.pop()
        if mx == 26: return 26
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < R and 0 <= ny < C:
                if board[nx][ny] in ans: continue
                if check[nx][ny] != ans + board[nx][ny]:
                    flag = False
                    check[nx][ny] = ans + board[nx][ny]
                    q.add((nx, ny, ans + board[nx][ny]))
        if flag:
            mx = max(mx, len(ans))
    return mx

if __name__ == '__main__':
    R, C = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
    check = [[''] * C for _ in range(R)]
    print(bfs(0, 0))