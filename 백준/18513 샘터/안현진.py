import sys
from collections import deque

def bfs(water):
    q = deque()
    dx = [1, -1]
    for i in water:
        q.append(i)
        line[i] = 0
    cnt = 0
    total = 0
    while q:
        cnt += 1
        size = len(q)
        for _ in range(size):
            x = q.popleft()
            for k in range(2):
                nx = x + dx[k]
                if nx not in line.keys():
                    line[nx] = cnt
                    q.append(nx)
                    res.append(nx)
                    total += 1
                    if total == M:
                        return

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    line = {}
    water = list(map(int, sys.stdin.readline().split()))
    res = []
    result = 0
    bfs(water)
    for i in res:
        result += line[i]
    print(result)