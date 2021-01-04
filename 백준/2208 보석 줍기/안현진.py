import sys

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    arr = [0] * N
    value = 0
    prefix_sum = [0]
    for i in range(N):
        arr[i] = int(input())
        value += arr[i]
        prefix_sum.append(value)
    ans, tmp = 0, 0
    for i in range(M - 1, N):
        tmp = min(tmp, prefix_sum[i - (M - 1)])
        ans = max(ans, prefix_sum[i + 1] - tmp)
    print(ans)