from itertools import combinations
from collections import deque
def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()

def bfs(board, delivery_home):
    dist = [[0] * N for _ in range(N)]
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    check = [[False] * N for _ in range(N)]
    tmp = 0
    q = deque()
    for home in delivery_home:
        a, b = home
        q.append(home)
        check[a][b] = True

    while q:
        size = len(q)
        for _ in range(size):
            a, b = q.popleft()
            for k in range(4):
                nx, ny = a + dx[k], b + dy[k]
                if 0 <= nx < N and 0 <= ny < N:
                    if not check[nx][ny]:
                        dist[nx][ny] = dist[a][b] + 1
                        q.append((nx, ny))
                        check[nx][ny] = True
                        if board[nx][ny] == 1:
                            tmp += dist[nx][ny]
    for home in delivery_home:
        a, b = home
        tmp += board[a][b]
    for a1 in range(N):
        for a2 in range(N):
            if board[a1][a2] == 1 and dist[a1][a2] == 0:
                return 9223372036854775807
    return tmp
if __name__ == '__main__':
    t = int(input())
    for tc in range(1, t + 1):
        N = int(input())
        board = [[int(x) for x in input().split()] for _ in range(N)]
        food_delivery = []
        res = 9223372036854775807
        for i in range(len(board)):
            for j in range(len(board[i])):
                if board[i][j] > 1:
                    food_delivery.append((i, j))
        for i in range(len(food_delivery)):
            for y in combinations(food_delivery, i + 1):
                res = min(res, bfs(board, y))
        if res == 9223372036854775807:
            print(f'#{tc} {0}')
        else:
            print(f'#{tc} {res}')

