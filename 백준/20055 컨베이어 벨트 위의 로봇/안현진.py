import sys
from collections import deque

def shift(seq, n):
    n = n % len(seq)
    return seq[n:] + seq[:n]

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    check = [False] * (2 * N)
    q = deque() # 로봇의 위치 인덱스
    ans = 1
    while True:
        arr = shift(arr, -1)
        tmp_q = deque()
        while q:
            x = q.popleft()
            if x == N - 1 or x == N - 2:
                check[x] = False
                continue
            if x + 2 < N and check[x]:
                if not check[x + 2] and arr[x + 2] > 0:
                    arr[x + 2] -= 1
                    check[x] = False
                    check[x + 2] = True
                    tmp_q.append(x + 2)
                elif not check[x + 1]:
                    check[x] = False
                    check[x + 1] = True
                    tmp_q.append(x + 1)
        if arr[0] > 0 and not check[0]:
            check[0] = True
            arr[0] -= 1
            tmp_q.append(0)
        q = tmp_q
        zero_count = 0
        for y in arr:
            if y == 0: zero_count += 1
        if zero_count >= K: break
        ans += 1
    print(ans)
