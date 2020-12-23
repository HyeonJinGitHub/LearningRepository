import sys
from collections import deque
from collections import defaultdict

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

if __name__ == '__main__':
    N, L, R = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    ans = 0
    while True:
        check = [[False] * N for _ in range(N)]
        group = defaultdict(lambda: set())
        group_count = 1
        for i in range(N):
            for j in range(N):
                if not check[i][j]:
                    q = deque([(i, j)])
                    group[group_count].add((i, j))
                    check[i][j] = True
                    while q:
                        x, y = q.popleft()
                        for k in range(4):
                            ni, nj = x + dx[k], y + dy[k]
                            if 0 <= ni < N and 0 <= nj < N:
                                if not check[ni][nj]:
                                    if L <= abs(board[ni][nj] - board[x][y]) <= R:
                                        group[group_count].add((ni, nj))
                                        q.append((ni, nj))
                                        check[ni][nj] = True
                    group_count += 1
        flag = False
        for i in range(1, group_count):
            if len(group[i]) == 1: continue
            flag = True
            tmp = 0
            for y in group[i]:
                tmp += board[y[0]][y[1]]
            for y in group[i]:
                board[y[0]][y[1]] = tmp // len(group[i])

        if not flag:
            print(ans)
            break
        ans += 1
