import sys
from collections import defaultdict

if __name__ == '__main__':
    T = int(input())
    n = int(input())
    A = list(map(int, sys.stdin.readline().split()))
    m = int(input())
    B = list(map(int, sys.stdin.readline().split()))

    A_sum = defaultdict(int)
    for i in range(n):
        tmp = 0
        for j in range(i, n):
            tmp += A[j]
            A_sum[tmp] += 1
    ans = 0
    for i in range(m):
        tmp = 0
        for j in range(i, m):
            tmp += B[j]
            if A_sum[T - tmp]: ans += A_sum[T - tmp]
    print(ans)