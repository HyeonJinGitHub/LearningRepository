import sys
from collections import deque

if __name__ == '__main__':
    for _ in range(int(input())):
        N, M, K = map(int, sys.stdin.readline().split())
        arr = list(map(int, sys.stdin.readline().split()))
        right = 0
        tmp, ans = 0, 0
        q = deque()
        for left in range(N):
            while right < N + M - 1 and len(q) < M:
                q.append(arr[right % N])
                tmp += arr[right % N]
                right += 1
            if tmp < K: ans += 1
            q.popleft()
            tmp -= arr[left]
            left += 1
            if N == M: break
        print(ans)
