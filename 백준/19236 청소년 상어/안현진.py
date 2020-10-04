import sys
from collections import deque
from copy import deepcopy

def bfs():
    q = deque()
    dx = [-1, -1, 0, 1, 1, 1, 0, -1]
    dy = [0, -1, -1, -1, 0, 1, 1, 1]
    result = 0
    q.append((board, shark, score, fish))

    while q:
        nboard, nshark, nscore, nfish = q.popleft()

        for i in range(16):
            if nfish[i][2] == -1:
                continue
            else:
                x, y, d = nfish[i]
                nx, ny = x + dx[d], y + dy[d]
                if 0 <= nx < 4 and 0 <= ny < 4 and nboard[nx][ny] != -1:
                    if nboard[nx][ny] != 0:
                        tmp = nboard[nx][ny]
                        nboard[nx][ny], nboard[x][y] = i + 1, tmp
                        nfish[tmp - 1][0], nfish[tmp - 1][1] = x, y
                        nfish[i][0], nfish[i][1] = nx, ny
                    else:
                        nboard[nx][ny], nboard[x][y] = i + 1, 0
                        nfish[i][0], nfish[i][1] = nx, ny
                else:
                    for _ in range(1, 8):
                        d = (d + 1) % 8
                        nx, ny = x + dx[d], y + dy[d]
                        if 0 <= nx < 4 and 0 <= ny < 4 and nboard[nx][ny] != -1:
                            if nboard[nx][ny] != 0:
                                tmp = nboard[nx][ny]
                                nboard[nx][ny], nboard[x][y] = i + 1, tmp
                                nfish[tmp - 1][0], nfish[tmp - 1][1] = x, y
                                nfish[i][0], nfish[i][1], nfish[i][2] = nx, ny, d
                            else:
                                nboard[nx][ny], nboard[x][y] = i + 1, 0
                                nfish[i][0], nfish[i][1], nfish[i][2] = nx, ny, d
                            break
        sx, sy, sd = nshark
        flag = True
        for c in range(1, 4):
            nsx, nsy = sx + c * dx[sd], sy + c * dy[sd]
            if 0 <= nsx < 4 and 0 <= nsy < 4 and nboard[nsx][nsy] > 0:
                nboard2 = deepcopy(nboard)
                nfish2 = deepcopy(nfish)
                tmp = nboard2[nsx][nsy]
                nboard2[sx][sy] = 0
                nboard2[nsx][nsy] = -1
                nsd = nfish2[tmp - 1][2]
                nfish2[tmp - 1][2] = -1
                q.append((nboard2, [nsx, nsy, nsd], nscore + tmp, nfish2))
                flag = False
        if flag:
            result = max(result, nscore)
    print(result)
if __name__ == '__main__':
    board = [[0] * 4 for _ in range(4)]
    fish = [[0, 0, 0] for _ in range(16)]
    for i in range(4):
        s = list(map(int, sys.stdin.readline().split()))
        for j in range(0, 8, 2):
            board[i][j // 2] = s[j]
            fish[s[j] - 1] = [i, j // 2, s[j + 1] - 1]
    shark = [0, 0, fish[board[0][0] - 1][2]]
    fish[board[0][0] - 1][2] = -1
    score = board[0][0]
    board[0][0] = -1
    bfs()