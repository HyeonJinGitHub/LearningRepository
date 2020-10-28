import sys
from collections import deque

def get_direction(s):
    if s == '-':
        return [0, 2]
    elif s == '|':
        return [1, 3]
    elif s == '+' or s == 'M' or s == 'Z':
        return [0, 1, 2, 3]
    elif s == '1':
        return [0, 1]
    elif s == '2':
        return [0, 3]
    elif s == '3':
        return [2, 3]
    elif s == '4':
        return [1, 2]

def bfs(x, y, direction):
    global fx, fy
    q = deque()
    q.append([x, y, direction])
    check[x][y] = True

    while q:
        x, y, direction = q.popleft()
        for d in direction:
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < R and 0 <= ny < C and not check[nx][ny]:
                if board[nx][ny] != '.':
                    check[nx][ny] = True
                    ndirection = get_direction(board[nx][ny])
                    q.append([nx, ny, ndirection])
                else:
                    if board[x][y] == 'M' or board[x][y] == 'Z': continue
                    if not fx and not fy:
                        fx, fy = nx + 1, ny + 1
                    nd = (d + 2) % 4
                    if nd not in check_list:
                        check_list.append(nd)

if __name__ == '__main__':
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    R, C = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
    check = [[False] * C for _ in range(R)]

    for i in range(R):
        for j in range(C):
            if board[i][j] == 'M':
                m_x, m_y = i, j
            if board[i][j] == 'Z':
                z_x, z_y = i, j

    check_list, fx, fy = [], 0, 0

    bfs(m_x, m_y, [0, 1, 2, 3])
    bfs(z_x, z_y, [0, 1, 2, 3])

    for i in range(R):
        for j in range(C):
            if board[i][j] != '.' and not check[i][j]:
                bfs(i, j, get_direction(board[i][j]))

    check_list.sort()

    if len(check_list) == 4:
        print(fx, fy, '+')
    else:
        block_list = ['|', '-', '1', '2', '3', '4']
        for res in block_list:
            if check_list == get_direction(res):
                print(fx, fy, res)