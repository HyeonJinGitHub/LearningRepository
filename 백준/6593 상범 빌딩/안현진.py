import sys
from collections import deque

def bfs(x, y, z):
    dx = [1, 0, -1, 0, 0, 0]
    dy = [0, 1, 0, -1, 0, 0]
    dz = [0, 0, 0, 0, 1, -1]
    dist = [[[0] * C for _ in range(R)] for _ in range(L)]
    q = deque()
    q.append((x, y, z))

    while q:
        x, y, z = q.popleft()
        for k in range(6):
            nx, ny, nz = x + dx[k], y + dy[k], z + dz[k]
            if 0 <= nx < L and 0 <= ny < R and 0 <= nz < C:
                if a[nx][ny][nz] == '.' and dist[nx][ny][nz] == 0:
                    dist[nx][ny][nz] = dist[x][y][z] + 1
                    q.append((nx, ny, nz))
                if a[nx][ny][nz] == 'E':
                    dist[nx][ny][nz] = dist[x][y][z] + 1
                    print(f'Escaped in {dist[nx][ny][nz]} minute(s).')
                    return
    print('Trapped!')

if __name__ == '__main__':
    while True:
        L, R, C = map(int, input().split())
        if L == 0 and R == 0 and C == 0:
            break
        a = [[[] * C for _ in range(R)] for _ in range(L)]
        for i in range(L):
            a[i] = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
            input()
        for i in range(L):
            for j in range(R):
                for k in range(C):
                    if a[i][j][k] == 'S':
                        bfs(i, j, k)

# 아래는 dx dy 만으로 3차원 배열이 아닌 2차원 배열을 연결하여 계산하는 방법이다.
# 하지만 50 % 정도에서 계속 틀렸다고 한다. 왜 틀렸는지 모르겠다... 해볼 수 있는 테스트케이스는 모두 해보고 정답 확인하였는데 제출하면 틀렸다고 한다...
# 이유를 알고 싶다 ㅠ.ㅠ

# import sys
# from collections import deque
#
# def bfs(x, y):
#     dx = [1, 0, -1, 0, R + 1, -(R+1)]
#     dy = [0, 1, 0, -1, 0, 0]
#     check = [[False] * C for _ in range(R + 1)]
#     dist = [[0] * C for _ in range(R + 1)] * L
#     for _ in range(L):
#         tmp1 = [[False] * C for _ in range(R + 1)]
#         tmp2 = [[0] * C for _ in range(R + 1)]
#         check += tmp1
#         dist += tmp2
#     q = deque()
#     q.append((x, y))
#     check[x][y] = True
#     while q:
#         x, y = q.popleft()
#         for k in range(6):
#             nx, ny = x + dx[k], y + dy[k]
#             if 0 <= nx < L * (R + 1) and 0 <= ny < C:
#                 if not check[nx][ny] and (matrix[nx][ny] == '.' or matrix[nx][ny] == 'E'):
#                     check[nx][ny] = True
#                     dist[nx][ny] = dist[x][y] + 1
#                     q.append((nx, ny))
#                     if matrix[nx][ny] == 'E':
#                         return dist[nx][ny]
#     return -1
#
# if __name__ == '__main__':
#     while True:
#         L, R, C = map(int, input().split())
#         if L == 0 and R == 0 and C == 0:
#             break
#         matrix = []
#         for i in range(L):
#             tmp = [list(map(str, sys.stdin.readline().rstrip())) for y in range(R)]
#             t = [input()]
#             matrix += (tmp + [['#'] * C])
#         flag = False
#         res = 0
#         for i in range(len(matrix)):
#             for j in range(len(matrix[i])):
#                 if matrix[i][j] == 'S':
#                     res = bfs(i, j)
#                     flag = True
#                     break
#             if flag:
#                 break
#         if res == -1:
#             print('Trapped!')
#         else:
#             print(f'Escaped in {res} minute(s).')