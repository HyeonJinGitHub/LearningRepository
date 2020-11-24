import sys
import heapq

# 사람의 인덱스와 의자의 인덱스를 만들어서 각 사람과 각 의자 사이의 거리를 기준으로 우선순위큐에 넣어준다(dist, person_idx, chair_idx)
# 우선순위큐에 있는 값을 하나씩 빼면서 의자별로 사람 방문 여부 계산한다.
# 큐를 빠져나오고 나서도 남아있는 의자의 인덱스가 있으면 모두 계산한다.

def cal(chair_idx):
    # 의자 번호 인덱스를 통해 의자 방문 횟수 계산
    global ans
    for i in range(len(chair_idx)):
        cidx = chair_idx[i]
        seated[cidx] += 1
        if seated[cidx] == 2:
            ans += 1

if __name__ == '__main__':
    R, C = map(int, sys.stdin.readline().split())
    board = [list(map(str, sys.stdin.readline().rstrip())) for _ in range(R)]
    chair, people = [], []
    seated = [0] * 10000
    finished = [False] * 10000
    ans = 0
    chair_idx = []
    hq = []
    for i in range(R):
        for j in range(C):
            if board[i][j] == 'X':
                people.append((i, j))
            elif board[i][j] == 'L':
                chair.append((i, j))
    # 각 사람과 의자 사이의 거리, 사람 인덱스, 의자 인덱스를 우선순위큐에 넣어준다.
    for pidx, person in enumerate(people):
        px, py = person[0], person[1]
        for cidx, ch in enumerate(chair):
            cx, cy = ch[0], ch[1]
            dist = (px - cx) ** 2 + (py - cy) ** 2
            heapq.heappush(hq, (dist, pidx, cidx))

    # 최소 1개의 의자와 1명의 사람이 있기 때문에 미리 한번 heappop 을 해준다.
    dist, pidx, cidx = heapq.heappop(hq)
    chair_idx.append(cidx) # chair_idx에 값이 들어갔다는 것은 어떤 사람이 의자를 방문하는 경우
    finished[pidx] = True

    while hq:
        nd, np, nc = heapq.heappop(hq)
        if dist != nd: # 거리가 이전 것과 다르면
            dist = nd # 거리 변경
            if chair_idx: # 기존에 계산하지 않은 의자 인덱스가 남아있으면
                cal(chair_idx) # 의자 인덱스를 가지고 의자 방문 계산
                chair_idx.clear() # 의자 인덱스 비워줌
        if not seated[nc] and not finished[np]: # 아직 방문하지 않은 의자가 있고 그 사람이 한번도 어떤 의자를 방문한적 없으면
            chair_idx.append(nc) # 의자 인덱스에 추가
            finished[np] = True # 사람 인덱스 True로 변경
    if chair_idx: # 남아있는 계산되지 않은 의자 인덱스가 있으면
        cal(chair_idx) # 계산
    print(ans)
