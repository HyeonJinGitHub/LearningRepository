import sys
from collections import deque
import heapq

def print_arr(arr):
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            print(arr[i][j], end=' ')
        print()
    print()

def tree_bfs(tree):
    q = deque(tree)

    while q:
        size = len(q)
        for _ in range(size):
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if tree_dist[nx][ny] == -1:
                        tree_dist[nx][ny] = tree_dist[x][y] + 1
                        q.append((nx, ny))

def wolf_bfs(wolf):
    q = []
    w_x, w_y = wolf[0][0], wolf[0][1]

    heapq.heappush(q, (-tree_dist[w_x][w_y], (-tree_dist[w_x][w_y], (w_x, w_y))))

    while q:
        d, (mn, (x, y)) = heapq.heappop(q)
        if tree_dist[x][y] == -1: continue
        tree_dist[x][y] = -1
        if board[x][y] == 'J':
            print(abs(mn))
            return
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < M and tree_dist[nx][ny] != -1:
                heapq.heappush(q, (-tree_dist[nx][ny], (max(mn, -tree_dist[nx][ny]), (nx, ny))))

if __name__ == '__main__':
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(N)]
    tree, wolf = [], []
    tree_dist = [[-1] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if board[i][j] == '+':
                tree.append((i, j))
                tree_dist[i][j] = 0
            if board[i][j] == 'V':
                wolf.append((i, j))

    tree_bfs(tree)
    wolf_bfs(wolf)