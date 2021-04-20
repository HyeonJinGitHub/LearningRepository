from collections import defaultdict

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def solution():
    time = 0
    while True:
        if time == M: break
        tmp = defaultdict(lambda :[])
        for i in range(N):
            for j in range(N):
                if not board[i][j]: continue
                now, d = cell[board[i][j]]
                nx, ny = i + dx[d], j + dy[d]
                if nx == 0 or nx == N - 1 or ny == 0 or ny == N - 1:
                    if d == 0: d = 1
                    elif d == 1: d = 0
                    elif d == 2: d = 3
                    elif d == 3: d = 2
                    tmp[(nx, ny)].append((board[i][j], now // 2, d))
                    board[i][j] = 0
                    continue
                tmp[(nx, ny)].append((board[i][j], now, d))
                board[i][j] = 0
        for key in tmp.keys():
            if len(tmp[key]) == 1:
                p, now, d = tmp[key][0]
                board[key[0]][key[1]] = p
                cell[p] = (now, d)
                continue
            elif len(tmp[key]) > 1:
                s = 0
                tmp_p = 0
                tmp_now = 0
                tmp_d = 0
                for (p, now, d) in tmp[key]:
                    s += now
                    if tmp_now < now:
                        tmp_now = now
                        tmp_d = d
                        tmp_p = p
                    del cell[p]
                board[key[0]][key[1]] = tmp_p
                cell[tmp_p] = (s, tmp_d)
        time += 1

if __name__ == '__main__':
    for tc in range(int(input())):
        N, M, K = map(int, input().split())
        board = [[0] * N for _ in range(N)]
        cell = dict()
        for i in range(K):
            x, y, c, d = map(int, input().split())
            board[x][y] = i + 1
            cell[i + 1] = (c, d - 1)
        solution()
        ans = 0
        for i in range(N):
            for j in range(N):
                if not board[i][j]: continue
                ans += cell[board[i][j]][0]
        print('#{0} {1}'.format(tc + 1, ans))

