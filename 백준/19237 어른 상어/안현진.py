import sys
from collections import defaultdict

def solution(board, t, scent, shark_location, dir_priority):
    global M
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    time = 0

    while True:
        if time > 1000:
            print(-1)
            return
        elif M == 1:
            print(time)
            return
        # 상어 이동
        time += 1
        for i in range(N*N):
            r, c = i // N, i % N
            if t[r][c] == 0: continue
            shark_number = board[(r, c)][0]
            board.pop((r, c))
            t[r][c] = 0
            tmp_dir1, tmp_dir2 = [], []
            for k in range(4):
                nr, nc = r + dx[k], c + dy[k]
                if 0 <= nr < N and 0 <= nc < N:
                    if not shark_location[(nr, nc)]:
                        tmp_dir1.append(k)
                    else:
                        if shark_location[(nr, nc)][0][0] == shark_number:
                            tmp_dir2.append(k)
            # 4 방향 중 빈공간이 있는 경우
            if len(tmp_dir1) > 0:
                if len(tmp_dir1) == 1:
                    d[shark_number] = tmp_dir1.pop()
                else:
                    for k in range(len(dir_priority[shark_number][d[shark_number]])):
                        if dir_priority[shark_number][d[shark_number]][k] in tmp_dir1:
                            d[shark_number] = dir_priority[shark_number][d[shark_number]][k]
                            break
            else:
                for k in range(len(dir_priority[shark_number][d[shark_number]])):
                    if dir_priority[shark_number][d[shark_number]][k] in tmp_dir2:
                        d[shark_number] = dir_priority[shark_number][d[shark_number]][k]
                        break
            nr, nc = r + dx[d[shark_number]], c + dy[d[shark_number]]
            board[(nr, nc)].append(shark_number)
        # 상어 잡아먹기 or t 번호 매기기
        for i in range(N*N):
            r, c = i // N, i % N
            if not board[(r, c)]: continue
            board[(r, c)].sort()
            super_shark = board[(r, c)][0]
            dead_shark_number = []
            for k in range(1, len(board[(r, c)])):
                dead_shark_number.append(board[(r, c)][k])
                M -= 1
            del board[(r, c)]
            board[(r, c)].append(super_shark)
            for k in range(len(shark_location[(r, c)])):
                if super_shark == shark_location[(r, c)][k][0]:
                    del shark_location[(r, c)][k]
                    scent[super_shark].remove((r, c))
            shark_location[(r, c)].append((super_shark, K))
            scent[super_shark].append((r, c))
            t[r][c] = super_shark
        # 흔적 1씩 지우기
        for i in range(N*N):
            r, c = i // N, i % N
            if not shark_location[(r, c)]: continue
            if t[r][c] != 0: continue
            for shark_l in shark_location[(r, c)]:
                shark, k = shark_l
                k -= 1
                shark_location[(r, c)].remove(shark_l)
                if k > 0:
                    shark_location[(r, c)].append((shark, k))
                else:
                    scent[shark].remove((r, c))

if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    t = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    board = defaultdict(lambda: [])
    scent = defaultdict(lambda: [])  # scent 는 1번부터 M번까지 상어
    shark_location = defaultdict(lambda: [])
    for t1 in range(N):
        for t2 in range(N):
            if t[t1][t2] != 0:
                scent[t[t1][t2]].append((t1, t2))
                shark_location[(t1, t2)].append((t[t1][t2], K))
                board[(t1, t2)].append(t[t1][t2])
    d = [0] + list(map(int, sys.stdin.readline().split()))
    for i in range(len(d)):
        d[i] -= 1  # 방향은 -1
    dir_priority = defaultdict(lambda: [])
    for i in range(1, M + 1):
        for j in range(4):
            tmp = list(map(int, sys.stdin.readline().split()))
            for q in range(len(tmp)):
                tmp[q] -= 1  # 우선순위에 따른 방향 -1
            dir_priority[i].append(tmp)
    solution(board, t, scent, shark_location, dir_priority)