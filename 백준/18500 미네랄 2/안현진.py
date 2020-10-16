import sys
from collections import deque

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()

def move(group_num, d, group):
    q = deque()
    for i in range(R):
        for j in range(C):
            if group[i][j] == group_num:
                q.append((i, j))
    for i in range(C):
        lx = -1
        for j in range(R):
            if board[j][i] == '.': continue
            if group[j][i] == group_num:
                lx = j
            if lx != -1 and group[j][i] != group_num and group[j][i] != 0:
                if d < j - lx: continue
                else:
                    d = j - 1 - lx
    while q:
        x, y = q.pop()
        board[x+d][y] = board[x][y]
        board[x][y] = '.'

def bfs(x, y, visit, group, group_num):
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    q = deque([(x, y)])
    visit[x][y] = True
    l_x = x
    group[x][y] = group_num
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < R and 0 <= ny < C:
                if not visit[nx][ny] and board[nx][ny] == 'x':
                    visit[nx][ny] = True
                    group[nx][ny] = group_num
                    if nx > l_x:
                        l_x = nx
                    q.append((nx, ny))
    return l_x

def solution(board, cmd):
    for i in range(len(cmd)):
        c = cmd[i]
        h = R - c
        if i % 2 == 0:
            for j in range(C):
                if board[h][j] == 'x':
                    board[h][j] = '.'
                    break
        else:
            for j in range(C - 1, -1, -1):
                if board[h][j] == 'x':
                    board[h][j] = '.'
                    break
        group_num = 1
        group = [[0] * C for _ in range(R)]
        visit = [[False] * C for _ in range(R)]
        group_dict = {}
        for t in range(R*C):
            r, c = t // C, t % C
            if board[r][c] == 'x' and not visit[r][c]:
                lx = bfs(r, c, visit, group, group_num)
                group_dict[group_num] = (group_num, lx)
                group_num += 1
        for t in range(1, group_num):
            if group_dict[t][1] != R - 1:
                d = R - 1 - group_dict[t][1]
                move(t, d, group)
        #print_arr(group)
        #print_arr(board)

if __name__ == '__main__':
    R, C = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
    N = int(input())
    cmd = list(map(int, sys.stdin.readline().split()))
    solution(board, cmd)
    for i in range(R):
        for j in range(C):
            print(board[i][j], end='')
        print()
