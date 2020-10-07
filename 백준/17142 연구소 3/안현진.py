import sys
from itertools import combinations
from collections import deque

def bfs(zero_cnt, nboard, virus):
    q = deque(virus)
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    cnt = 0

    for i in virus: nboard[i[0]][i[1]] = -1

    while q:
        cnt += 1
        if cnt >= res:
            return -1
        size = len(q)
        for _ in range(size):
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < N:
                    if nboard[nx][ny] == -1 or nboard[nx][ny] == 1: continue
                    else:
                        if nboard[nx][ny] == 0:
                            zero_cnt -= 1
                            if zero_cnt == 0:
                                return cnt
                        nboard[nx][ny] = -1
                        q.append((nx, ny))
    if zero_cnt > 0:
        return -1
    return cnt

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[] for _ in range(N)]
    zero_cnt = 0
    res = 1000000
    virus_arr = []

    for i in range(N):
        board[i] = list(map(int, sys.stdin.readline().split()))
        zero_cnt += board[i].count(0)
        for j in range(N):
            if board[i][j] == 2:
                virus_arr.append((i, j))

    if zero_cnt == 0:
        print(0)
    else:
        for v in combinations(virus_arr, M):
            tmp_board = [[] for _ in range(N)]
            for i in range(N):
                tmp_board[i] = board[i][:]
            tmp = bfs(zero_cnt, tmp_board, list(v))
            if tmp != -1:
                res = min(res, tmp)
        if res == 1000000:
            print(-1)
        else:
            print(res)

# 아래 코드는 직접 작성한 코드인데, Solve는 받았찌만 시간이 아슬아슬했다. 약 480~490 ms
# 위의 코드는 다른 사람의 코드를 참고한 것으로, 0의 개수를 미리 세어서 0을 방문할때마다 그 개수를 감소하여 0이 되었을 때의 시간을 출력하는 방법이다.
# 효율적으로 코드를 짜는 것의 중요성을 알게 된 것 같다.

# import sys
# from itertools import combinations
# from collections import deque
# from copy import deepcopy
#
# def bfs(virus):
#     dx = [1, 0, -1, 0]
#     dy = [0, 1, 0, -1]
#     dist = [[0] * N for _ in range(N)]
#     q = deque(virus)
#     nboard = deepcopy(board)
#     cnt = -1
#
#     for i in virus: nboard[i[0]][i[1]] = -1
#
#     while q:
#         size = len(q)
#         for _ in range(size):
#             x, y = q.popleft()
#             for k in range(4):
#                 nx, ny = x + dx[k], y + dy[k]
#                 if 0 <= nx < N and 0 <= ny < N:
#                     if nboard[nx][ny] == 0 and dist[nx][ny] == 0:
#                         dist[nx][ny] = dist[x][y] + 1
#                         q.append((nx, ny))
#                         cnt = max(cnt, dist[nx][ny])
#                     if nboard[nx][ny] == 2 and dist[nx][ny] == 0:
#                         dist[nx][ny] = dist[x][y] + 1
#                         q.append((nx, ny))
#                         cnt = min(cnt, dist[nx][ny])
#     for i in range(N):
#         for j in range(N):
#             if nboard[i][j] == 0 and dist[i][j] == 0:
#                 return -1
#     return cnt
#
# if __name__ == '__main__':
#     N, M = map(int, sys.stdin.readline().split())
#     board = [[] for _ in range(N)]
#     virus_arr = []
#     flag = False
#     res = 1000000
#     for i in range(N):
#         board[i] = list(map(int, sys.stdin.readline().split()))
#         if board[i].count(0) != 0:
#             flag = True
#         for j in range(len(board[i])):
#             if board[i][j] == 2:
#                 virus_arr.append((i, j))
#     if not flag:
#         print(0)
#     else:
#         for v in combinations(virus_arr, M):
#             tmp = bfs(list(v))
#             if tmp != -1:
#                 res = min(res, tmp)
#         if res == 1000000:
#             print(-1)
#         else:
#             print(res)