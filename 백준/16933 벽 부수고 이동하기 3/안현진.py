import sys
from collections import deque

def bfs(x, y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    check = [[[False] * (K + 1) for _ in range(M)] for _ in range(N)]
    q = deque()
    day = True

    q.append((x, y, 0, 1))
    check[x][y][0] = True

    while q:
        p = len(q)
        for _ in range(p):
            x, y, w, d = q.popleft()
            if x == N - 1 and y == M - 1:
                return d
            for k in range(4):
                nx, ny, nw, nd = x + dx[k], y + dy[k], w + 1, d + 1
                if 0 <= nx < N and 0 <= ny < M:
                    if a[nx][ny] == 1 and w < K and not check[nx][ny][nw]:
                        if day:
                            q.append((nx, ny, nw, nd))
                            check[nx][ny][nw] = True
                        else:
                            q.append((x, y, w, nd))
                    if a[nx][ny] == 0 and not check[nx][ny][w]:
                        q.append((nx, ny, w, nd))
                        check[nx][ny][w] = True
        day = not day
    return -1

if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    a = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(N)]
    print(bfs(0, 0))

# 아래 코드는 시간초과 나는 코드이다.
# 직접 찾아본 대부분의 테스트케이스는 통과하는데, 어느 테스트케이스에서 통과하지 못하는지 궁금하다.
# 다음에 꼭 시간초과인 이유를 찾아봐야겠다.

# import sys
# from collections import deque
#
# def check():
#     global dist
#     MAX = 10 ** 8
#     res = MAX
#     for i in range(K + 1):
#         for j in range(2):
#             if dist[N - 1][M - 1][i][j] != 0:
#                 res = min(res, dist[N - 1][M - 1][i][j])
#     if res == MAX:
#         return -1
#     else:
#         return res
# def bfs(x, y, z, w):
#     global dist
#     dx = [1, 0, -1, 0]
#     dy = [0, 1, 0, -1]
#     q = deque()
#     q.append((x, y, z, w))
#     dist[x][y][z][w] = 1
#     while q:
#         x, y, z, w = q.popleft()
#         for k in range(4):
#             nx, ny = x + dx[k], y + dy[k]
#             if 0 <= nx < N and 0 <= ny < M:
#                 if w == 0 and a[nx][ny] == 0 and dist[nx][ny][z][w + 1] == 0:
#                     dist[nx][ny][z][w + 1] = dist[x][y][z][w] + 1
#                     if nx == N - 1 and ny == M - 1:
#                         res = check()
#                         return res
#                     q.append((nx, ny, z, w + 1))
#                 if w == 0 and a[nx][ny] == 1 and z < K and dist[nx][ny][z + 1][w + 1] == 0:
#                     dist[nx][ny][z + 1][w + 1] = dist[x][y][z][w] + 1
#                     if nx == N - 1 and ny == M - 1:
#                         res = check()
#                         return res
#                     q.append((nx, ny, z + 1, w + 1))
#                 if w == 1 and a[nx][ny] == 0 and dist[nx][ny][z][w - 1] >= 0:
#                     dist[nx][ny][z][w - 1] = dist[x][y][z][w] + 1
#                     if nx == N - 1 and ny == M - 1:
#                         res = check()
#                         return res
#                     q.append((nx, ny, z, w - 1))
#                 if w == 1 and a[nx][ny] == 1 and z < K and dist[nx][ny][z][w - 1] >= 0:
#                     dist[x][y][z][w - 1] = dist[x][y][z][w] + 1
#                     if nx == N - 1 and ny == M - 1:
#                         res = check()
#                         return res
#                     q.append((x, y, z, w - 1))
#     return check()
#
# if __name__ == '__main__':
#     N, M, K = map(int, sys.stdin.readline().split())
#     a = [list(map(int, sys.stdin.readline().rstrip())) for _ in range(N)]
#     dist = [[[[0] * 2 for _ in range(K + 1)] for _ in range(M)] for _ in range(N)]
#     result = bfs(0, 0, 0, 0)
#     print(result)