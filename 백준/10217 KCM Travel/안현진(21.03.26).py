import sys

INF = sys.maxsize

if __name__ == '__main__':
    for _ in range(int(input())):
        N, M, K = map(int, sys.stdin.readline().split())
        adj_list = [[] for _ in range(N + 1)]

        for _ in range(K):
            u, v, c, d = map(int, sys.stdin.readline().split())
            adj_list[u].append((v, c, d))

        dp = [[INF] * (M + 1) for _ in range(N + 1)]
        dp[1][0] = 0
        for money in range(M + 1):
            for now in range(1, N + 1):
                if dp[now][money] == INF: continue
                mn_dis = dp[now][money]
                for nxt, c, d in adj_list[now]:
                    nxt_money = money + c
                    if nxt_money > M: continue
                    dp[nxt][nxt_money] = min(dp[nxt][nxt_money], mn_dis + d)
        ans = min(dp[N])
        print(ans if ans != INF else 'Poor KCM')