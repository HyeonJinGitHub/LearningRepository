from collections import deque
from collections import defaultdict

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
SIZE = 400

def solution():
    global ans, res
    while True:
        q = deque()
        if ans == K:
            for i in range(SIZE):
                for j in range(SIZE):
                    if board[i][j] and live_cell[i][j] > 0:
                        res += 1
            break
        for i in range(SIZE):
            for j in range(SIZE):
                if not board[i][j]: continue
                # 비활성화 -> 활성화
                if board[i][j] and time[i][j] == 0 and not check[i][j]:
                    q.append((i, j, board[i][j]))
                    check[i][j] = True
                # 비활성화 상태
                if time[i][j]:
                    time[i][j] -= 1
                # 활성화 상태 이후 죽기까지 시간 체크
                if check[i][j] and live_cell[i][j] > 0:
                    live_cell[i][j] -= 1
        tmp = defaultdict(lambda :[])
        # 활성화 상태 세포들 이동
        while q:
            x, y, d = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if board[nx][ny]: continue
                tmp[(nx, ny)].append(d)
        # 동일한 곳에 2 개 이상의 세포 있을 경우 가장 큰 세포를 선택
        for k in tmp.keys():
            if len(tmp[k]) > 1:
                tmp[k].sort(reverse=True)
            time[k[0]][k[1]] = tmp[k][0]
            board[k[0]][k[1]] = time[k[0]][k[1]]
            live_cell[k[0]][k[1]] = board[k[0]][k[1]]
        ans += 1

if __name__ == '__main__':
    for tc in range(int(input())):
        board = [[0] * SIZE for _ in range(SIZE)]
        time = [[0] * SIZE for _ in range(SIZE)]
        check = [[False] * SIZE for _ in range(SIZE)]
        N, M, K = map(int, input().split())
        live_cell = [[0] * SIZE for _ in range(SIZE)]
        ans = 0
        for i in range(N):
            arr = list(map(int, input().split()))
            for j in range(len(arr)):
                board[i + SIZE // 2][j + SIZE // 2] = arr[j]
                time[i + SIZE // 2][j + SIZE // 2] = arr[j]
                live_cell[i + SIZE // 2][j + SIZE // 2] = arr[j]
        res = 0
        solution()

        print('#{0} {1}'.format(tc + 1, res))
