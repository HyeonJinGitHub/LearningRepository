import sys
import heapq

if __name__ == '__main__':
    N = int(input())
    hq = []
    for _ in range(N):
        a, b = map(int, sys.stdin.readline().split())
        heapq.heappush(hq, (a, b))
    q = []
    ans = 0
    while hq:
        a, b = heapq.heappop(hq)
        if not q:
            heapq.heappush(q, b)
            ans += 1
            continue
        while q:
            previous = heapq.heappop(q)
            if a < previous:
                ans += 1
                heapq.heappush(q, previous)
                heapq.heappush(q, b)
                break
            else:
                heapq.heappush(q, b)
                break
    print(ans)