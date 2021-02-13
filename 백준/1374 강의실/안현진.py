import sys
import heapq

if __name__ == '__main__':
    N = int(input())
    hq = []
    for _ in range(N):
        n, s, e = map(int, sys.stdin.readline().split())
        heapq.heappush(hq, (s, e))
    q = []
    ans = 0
    while hq:
        start, end = heapq.heappop(hq)
        if not q:
            heapq.heappush(q, end)
            ans += 1
            continue
        while q:
            previous = heapq.heappop(q)
            if start < previous:
                heapq.heappush(q, previous)
                heapq.heappush(q, end)
                ans += 1
                break
            else:
                heapq.heappush(q, end)
                break
    print(ans)