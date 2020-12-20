import sys
from collections import deque
from copy import deepcopy

def bfs(board, virus):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    q = deque(virus)
    # 원본 board 훼손하지 않기 위해 deepcopy로 복사
    tmp_board = deepcopy(board)
    tmp = 0

    # bfs
    while q:
        size = len(q)
        # tmp_board 에 존재하는 바이러스를 동시에 퍼트리게 하기 위해 len(q) 만큼 반복문 실행
        for _ in range(size):
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < M:
                    if tmp_board[nx][ny] != 0: continue
                    tmp_board[nx][ny] = 2
                    q.append((nx, ny))
    for i in range(N):
        for j in range(M):
            if tmp_board[i][j] == 0: tmp += 1
    return tmp

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    virus = []
    ans = 0
    for i in range(N):
        for j in range(M):
            if board[i][j] == 2: virus.append((i, j))

    # 1번 벽 세우기
    for x1 in range(N):
        for y1 in range(M):
            if board[x1][y1]: continue # 빈 공간이 아닐 경우 continue
            # 2번 벽 세우기
            for x2 in range(N):
                for y2 in range(M):
                    if board[x2][y2]: continue # 빈 공간이 아닐 경우 continue
                    # 3번 벽 세우기
                    for x3 in range(N):
                        for y3 in range(M):
                            if board[x3][y3]: continue # 빈 공간이 아닐 경우 continue
                            # 벽이 서로 다른 3가지가 될 수 있도록 같으면 continue
                            if x1 == x2 and y1 == y2: continue
                            if x2 == x3 and y2 == y3: continue
                            if x1 == x3 and y1 == y3: continue
                            # 벽 세우기
                            board[x1][y1] = 1
                            board[x2][y2] = 1
                            board[x3][y3] = 1
                            ans = max(ans, bfs(board, virus))
                            # 세웠던 벽 부수기
                            board[x1][y1] = 0
                            board[x2][y2] = 0
                            board[x3][y3] = 0
    print(ans)