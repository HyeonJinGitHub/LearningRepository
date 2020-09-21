from collections import deque

def bfs(x, y):
    dx = [-2, -1, 1, 2, 2, 1, -1, -2]
    dy = [1, 2, 2, 1, -1, -2, -2 ,-1]
    check = [[False for x in range(N)] for y in range(N)]
    dist = [[0 for x in range(N)] for y in range(N)]
    q = deque()
    q.append((x, y))
    check[x][y] = True

    while q:
        x, y = q.popleft()
        for k in range(8):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if not check[nx][ny]:
                    check[nx][ny] = True
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    if nx == dest_x and ny == dest_y:
                        print(dist[nx][ny])
                        return
    print(dist[dest_x][dest_y])
if __name__ == '__main__':
    for _ in range(int(input())):
        N = int(input())
        a = [[0 for x in range(N)] for y in range(N)]
        sx, sy = map(int, input().split())
        dest_x, dest_y = map(int, input().split())
        bfs(sx, sy)
