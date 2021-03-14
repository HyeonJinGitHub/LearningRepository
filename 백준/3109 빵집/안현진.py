import sys

dx = [-1, 0, 1]
dy = [1, 1, 1]

def dfs(x, y):
    global board, ans

    if y == C - 1:
        ans += 1
        return True
    for k in range(3):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < R and 0 <= ny < C:
            if board[nx][ny] == '.':
                board[nx][ny] = 'x'
                if dfs(nx, ny):
                    return True
    return False

if __name__ == '__main__':
    R, C = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
    ans = 0
    for i in range(R):
        dfs(i, 0)
    print(ans)