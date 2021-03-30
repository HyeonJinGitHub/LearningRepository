import sys
import heapq
from collections import deque

INF = sys.maxsize

def dijkstra(start):
    dp[start] = 0
    heapq.heappush(hq, (0, start))

    while hq:
        weight, now = heapq.heappop(hq)
        if dp[now] < weight: continue
        for w, nxt in adj_list[now]:
            nxt_weight = w + weight
            if dp[nxt] > nxt_weight:
                dp[nxt] = nxt_weight
                heapq.heappush(hq, (nxt_weight, nxt))

def bfs():
    q = deque()
    q.append(V)

    while q:
        after = q.popleft()
        if after == 1: continue
        for w, before in adj_list[after]:
            if dp[before] + w == dp[after]:
                if (before, after, w) not in shortest_path:
                    shortest_path.add((before, after, w))
                    q.append(before)

if __name__ == '__main__':
    V, E, P = map(int, sys.stdin.readline().split())
    adj_list = [[] for _ in range(V + 1)]
    for _ in range(E):
        a, b, c = map(int, sys.stdin.readline().split())
        adj_list[a].append((c, b))
        adj_list[b].append((c, a))
    hq = []
    dp = [INF] * (V + 1)
    dijkstra(1)

    shortest_path = set()
    bfs()

    for (before, after, weight) in shortest_path:
        if before == P or after == P:
            print('SAVE HIM')
            exit(0)
    print('GOOD BYE')
