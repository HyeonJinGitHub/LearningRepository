import sys

def knapsack(K, weight, value, N):
    dp = [[0 for _ in range(K + 1)] for _ in range(N + 1)]
    for i in range(N + 1):
        for w in range(K + 1):
            if i == 0 or w == 0:
                dp[i][w] = 0
            elif weight[i - 1] <= w:
                dp[i][w] = max(value[i - 1] + dp[i - 1][w-weight[i - 1]], dp[i-1][w])
            else:
                dp[i][w] = dp[i - 1][w]
    return dp[N][K]

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    weight, value = [], []
    for _ in range(N):
        w, v = map(int, sys.stdin.readline().split())
        weight.append(w)
        value.append(v)
    print(knapsack(K, weight, value, N))