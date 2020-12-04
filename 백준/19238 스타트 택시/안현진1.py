import sys
from collections import defaultdict
from collections import deque

ddx = [1, 0, -1, 0]
ddy = [0, 1, 0, -1]

def middle_bfs(x, y):
    q = deque([(x, y)])
    dist = [[-1] * N for _ in range(N)]
    dist[x][y] = 0
    g = []
    while q:
        x, y = q.popleft()
        if board[x][y] == 2: g.append((dist[x][y], x, y))
        for k in range(4):
            nx, ny = x + ddx[k], y + ddy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] == 1: continue
                if dist[nx][ny] == -1:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    g = sorted(g)
    return g

def taxi_bfs(x, y):
    global K
    q = deque([(x, y)])
    dist = [[-1] * N for _ in range(N)]
    dist[x][y] = 0
    g = []
    while q:
        x, y = q.popleft()
        if board[x][y] == 2: g.append((dist[x][y], x, y))
        for k in range(4):
            nx, ny = x + ddx[k], y + ddy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] == 1: continue
                if dist[nx][ny] == -1:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
    g = sorted(g)
    if len(g) != M:
        print(-1)
        exit(0)
    while g:
        d, x, y = g.pop(0)
        board[x][y] = 0
        K -= d
        if K < 0:
            print(-1)
            exit(0)
        for kx, ky in start_board.keys():
            if kx == x and ky == y:
                number = start_board[(kx, ky)][0]
                break
        start_board[(x, y)].clear()
        tmp = guest[number].pop(0)
        x1, y1, x2, y2, m_dist = tmp[0], tmp[1], tmp[2], tmp[3], tmp[4]
        if K - m_dist < 0:
            print(-1)
            exit(0)
        end_board[(x2, y2)].clear()
        K += m_dist
        g = middle_bfs(x2, y2)
    print(K)

def guest_bfs(x, y, x1, y1):
    q = deque([(x, y)])
    dist = [[0] * N for _ in range(N)]

    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + ddx[k], y + ddy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] != 1 and not dist[nx][ny]:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    if nx == x1 and ny == y1:
                        return dist[nx][ny]
    return 0

if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    tx, ty = map(int, sys.stdin.readline().split())
    guest = defaultdict(lambda :[])
    start_board, end_board = defaultdict(lambda :[]), defaultdict(lambda :[])
    for i in range(1, M + 1):
        sx, sy, dx, dy = map(int, sys.stdin.readline().split())
        cnt = guest_bfs(sx - 1, sy - 1, dx - 1, dy - 1)
        if cnt == 0:
            print(-1)
            exit(0)
        guest[i].append((sx - 1, sy - 1, dx - 1, dy - 1, cnt))
        start_board[(sx - 1, sy - 1)].append(i)
        end_board[(dx - 1, dy - 1)].append(i)
        board[sx - 1][sy - 1] = 2
    taxi_bfs(tx - 1, ty - 1)