import sys
from collections import deque

if __name__ == '__main__':
    N, d, k, c = map(int, sys.stdin.readline().split())
    arr = [0] * N
    count = [0] * (d + 1)
    cnt, ans = 0, 0
    q = deque()

    for i in range(N):
        arr[i] = int(input())
    for i in range(k):
        q.append(arr[i])
        if not count[arr[i]]:
            count[arr[i]] += 1
            cnt += 1
        else: count[arr[i]] += 1
    for i in range(N):
        q.popleft()
        if count[arr[i]]:
            count[arr[i]] -= 1
            if not count[arr[i]]:
                cnt -= 1
        q.append(arr[(i + k) % N])
        if not count[arr[(i + k) % N]]:
            count[arr[(i + k) % N]] += 1
            cnt += 1
        else:
            count[arr[(i + k) % N]] += 1
        if count[c]: ans = max(ans, cnt)
        else: ans = max(ans, cnt + 1)
    print(ans)
