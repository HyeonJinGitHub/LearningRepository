import sys
from collections import deque

if __name__ == '__main__':
    N, d, k, c = map(int, sys.stdin.readline().split())
    arr = []
    q = deque()
    check = [0] * (d + 1)
    res, cnt = 0, 0

    for _ in range(N):
        arr.append(int(sys.stdin.readline()))
    for i in range(k):
        q.append(arr[i])
        if check[arr[i]] == 0:
            check[arr[i]] += 1
            cnt += 1
        else:
            check[arr[i]] += 1
        res = max(res, cnt)
    for i in range(N):
        q.popleft()
        if check[arr[i]] != 0:
            check[arr[i]] -= 1
            if check[arr[i]] == 0:
                cnt -= 1
        q.append(arr[(i + k) % N])
        if check[arr[(i + k) % N]] == 0:
            check[arr[(i + k) % N]] += 1
            cnt += 1
        else:
            check[arr[(i + k) % N]] += 1
        if check[c] == 0:
            res = max(res, cnt + 1)
        else:
            res = max(res, cnt)
    sys.stdout.writelines(str(res))