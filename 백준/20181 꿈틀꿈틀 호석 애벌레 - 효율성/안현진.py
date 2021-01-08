import sys

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))

    dp = [0] * N
    lmax, ans = 0, 0
    tmp = 0
    left, right = 0, 0
    while True:
        if tmp >= K:
            lmax = 0 if left == 0 else max(lmax, dp[left - 1])
            dp[right - 1] = max(dp[right - 1], lmax + tmp - K)
            tmp -= arr[left]
            left += 1
        elif right == N: break
        else:
            tmp += arr[right]
            right += 1
    for i in range(N):
        ans = max(ans, dp[i])
    print(ans)