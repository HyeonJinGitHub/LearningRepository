import sys

def solution(pos, score):
    global visit, horse
    res = 0
    if pos == 10:
        return score
    b = [x[:] for x in horse]
    c = [x[:] for x in visit]
    for i in range(4):
        d, x = horse[i]
        prev_dice = dice[pos]
        nd, nx = d, x + prev_dice

        if x == INF: continue
        if d == 0 and (x in [5, 10, 15]):
            nd = x // 5
            nx = prev_dice
        if nd == 0 and nx == 20:
            nd = 4
            nx = 3
        if nd in [1, 2, 3] and len(a[nd]) <= nx:
            nx -= len(a[nd])
            nd = 4
        if len(a[nd]) <= nx:
            horse[i] = [0, INF]
            visit[d][x] = False
            res = max(res, solution(pos + 1, score))
        else:
            if visit[nd][nx]: continue
            horse[i] = [nd, nx]
            visit[nd][nx] = True
            visit[d][x] = False
            res = max(res, solution(pos + 1, score + a[nd][nx]))
        horse = [x[:] for x in b]
        visit = [x[:] for x in c]
    return res

if __name__ == '__main__':
    horse = [[0, 0] for _ in range(4)]
    visit = [[False] * 21 for _ in range(5)]
    a = [[2*i for i in range(21)], [10, 13, 16, 19], [20, 22, 24], [30, 28, 27, 26], [25, 30, 35, 40]]
    dice = list(map(int, sys.stdin.readline().split()))
    INF = 1e9
    print(solution(0, 0))