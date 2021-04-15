import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def bfs(x, y):
    q = deque([(x, y)])
    island[x][y] = island_num

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if board[nx][ny] and not island[nx][ny]:
                    island[nx][ny] = island_num
                    q.append((nx, ny))

def find_min_distance(now_island):
    dist = [[-1] * M for _ in range(N)]
    q = deque()
    for i in range(N):
        for j in range(M):
            if island[i][j] == now_island:
                dist[i][j] = 0
                q.append((i, j))
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M:
                if dist[nx][ny] == -1:
                    distance = 1
                    while True:
                        nx += dx[k]
                        ny += dy[k]
                        if nx < 0 or nx >= N or ny < 0 or ny >= M: break
                        if not dist[nx][ny]: break
                        if island[nx][ny] and island[nx][ny] != now_island:
                            if distance > 1:
                                min_island_distance[island[nx][ny]][now_island] = min(distance, min_island_distance[island[nx][ny]][now_island])
                                min_island_distance[now_island][island[nx][ny]] = min(distance, min_island_distance[now_island][island[nx][ny]])
                            break
                        distance += 1

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    island = [[0] * M for _ in range(N)]
    island_num = 1

    for i in range(N):
        for j in range(M):
            if board[i][j] and not island[i][j]:
                bfs(i, j)
                island_num += 1
    min_island_distance = [[10] * island_num for _ in range(island_num)]
    for i in range(1, island_num):
        find_min_distance(i)
    costs = []
    for i in range(1, island_num - 1):
        for j in range(i + 1, island_num):
            if min_island_distance[i][j] != 10:
                costs.append((i, j, min_island_distance[i][j]))
    ans = 0
    costs = sorted(costs, key=lambda x: x[2])
    check = [0] * island_num
    check[0], check[1] = 1, 1
    while sum(check) != island_num:
        for cost in costs:
            start, end, c = cost
            if check[start] or check[end]:
                if check[start] and check[end]: continue
                check[start] = 1
                check[end] = 1
                ans += c
                break
        else:
            ans = -1
            break
    print(ans)
