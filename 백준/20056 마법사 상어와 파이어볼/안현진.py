import sys
from collections import defaultdict
from collections import deque
import math

if __name__ == '__main__':
    dx = [-1, -1, 0, 1, 1, 1, 0, -1]
    dy = [0, 1, 1, 1, 0, -1, -1, -1]
    N, M, K = map(int, sys.stdin.readline().split())
    fire_ball = defaultdict(lambda :[])
    q = deque()

    for _ in range(M):
        r, c, m, s, d = map(int, sys.stdin.readline().split())
        q.append((r-1, c-1))
        fire_ball[(r-1, c-1)].append((m, s, d))

    while K > 0:
        tmp_q = set()
        tmp_fire_ball = defaultdict(lambda :[])

        while q:
            r, c = q.popleft()
            for y in fire_ball[(r, c)]:
                m, s, d = y[0], y[1], y[2]
                nx, ny = r + (dx[d] * s), c + (dy[d] * s)
                if nx <= 0 or nx >= N: nx %= N
                if ny <= 0 or ny >= N: ny %= N
                tmp_q.add((nx, ny))
                tmp_fire_ball[(nx, ny)].append((m, s, d))
            fire_ball[(r, c)].clear()

        for t in tmp_q:
            r, c = t[0], t[1]
            if len(tmp_fire_ball[(r, c)]) == 1:
                fire_ball[(r, c)].extend(tmp_fire_ball[(r, c)])
                q.append((r, c))
            elif len(tmp_fire_ball[(r, c)]) > 1:
                weight, speed, direction = 0, 0, []
                for y in tmp_fire_ball[(r, c)]:
                    weight += y[0]
                    speed += y[1]
                    direction.append(y[2])
                w = math.floor(weight / 5)
                if w == 0: continue
                s = math.floor(speed / len(tmp_fire_ball[(r, c)]))

                flag = False
                a, b = [], []

                for d in direction:
                    if d % 2 == 0: a.append(d)
                    else: b.append(d)

                if len(a) == 0 or len(b) == 0: flag = True
                else: flag = False

                if flag:
                    for d in [0, 2, 4, 6]:
                        fire_ball[(r, c)].append((w, s, d))
                else:
                    for d in [1, 3, 5, 7]:
                        fire_ball[(r, c)].append((w, s, d))
                q.append((r, c))
        K -= 1

    ans = 0
    while q:
        r, c = q.popleft()
        for y in fire_ball[(r, c)]:
            m, s, d = y[0], y[1], y[2]
            ans += m
    print(ans)