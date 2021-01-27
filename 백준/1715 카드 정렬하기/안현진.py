import sys
import heapq

if __name__ == '__main__':
    N = int(input())
    hq = []
    for _ in range(N):
        heapq.heappush(hq, int(sys.stdin.readline()))
    if N == 1:
        print(0)
        exit(0)
    ans = 0
    while len(hq) > 1:
        x = heapq.heappop(hq)
        y = heapq.heappop(hq)
        ans += (x + y)
        heapq.heappush(hq, (x + y))
    print(ans)