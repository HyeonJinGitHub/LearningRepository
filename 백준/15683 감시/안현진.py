import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

def solution(cnt):
    global check, ans
    if cnt == cctv_n: # 모든 CCTV 탐색했다면 사각지대 개수 세주기
        tmp = 0
        for i in range(N):
            for j in range(M):
                if not board[i][j] and not check[i][j]:
                    tmp += 1
        return tmp
    x, y = cctv[cnt][0], cctv[cnt][1]
    for k in range(4): # CCTV 의 4방향 확인
        new_dir = []
        if board[x][y] == 1: # 1번 CCTV : 현재 방향
            new_dir.append(k)
        elif board[x][y] == 2: # 2번 CCTV : 현재 방향 + 반대 방향
            new_dir.append(k)
            new_dir.append((k + 2) % 4)
        elif board[x][y] == 3: # 3번 CCTV : 현재 방향 + 왼쪽 90도 방향
            new_dir.append(k)
            new_dir.append((k - 1) % 4)
        elif board[x][y] == 4: # 4번 CCTV : 현재 방향 + 반대 방향 + 왼쪽 90도 방향
            new_dir.append(k)
            new_dir.append((k - 1) % 4)
            new_dir.append((k + 2) % 4)
        elif board[x][y] == 5: # 5번 CCTV : 4방향
            new_dir.append(k)
            new_dir.append((k - 1) % 4)
            new_dir.append((k + 1) % 4)
            new_dir.append((k + 2) % 4)
        q = deque()
        for d in new_dir: # CCTV 방향 개수 만큼 이동
            nx, ny = x + dx[d], y + dy[d]
            while 0 <= nx < N and 0 <= ny < M: # 특정 방향으로 끝까지 이동
                if not check[nx][ny] and board[nx][ny] != 6: # 방문하지 않았으며 벽이 아니면
                    check[nx][ny] = True # 방문
                    q.append((nx, ny))
                elif board[nx][ny] == 6: break # 벽이면 중단
                nx += dx[d]
                ny += dy[d]
        # 다음 CCTV 호출
        ans = min(ans, solution(cnt + 1))
        # 방문했던 곳 되돌려주기
        while q:
            qx, qy = q.popleft()
            if not board[qx][qy]:
                check[qx][qy] = False
        # 5번 CCTV 는 회전할 필요 없으므로 바로 break
        if board[x][y] == 5: break
    return ans

if __name__ == '__main__':
    N, M = map(int,sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    cctv = []
    cctv_n = 0 # 전체 CCTV 개수
    ans = 0 # 사각지대
    check = [[False] * M for _ in range(N)] # 방문 여부 확인
    for i in range(N):
        for j in range(M):
            if board[i][j] and board[i][j] != 6:
                cctv.append((i, j))
                check[i][j] = True
                cctv_n += 1
            if not board[i][j]:
                ans += 1 # 최대 사각지대 구하기
    solution(0)
    print(ans)