import sys
from collections import deque

if __name__ == '__main__':
    S = int(input())

    dp = [[-1] * 1001 for _ in range(1001)]
    dp[1][0] = 0
    q = deque([(1, 0)])
    while q:
        i, j = q.popleft()
        if i <= 1000:
            if dp[i][i] == -1:
                dp[i][i] = dp[i][j] + 1
                q.append((i, i))
        if i + j <= 1000:
            if dp[i + j][j] == -1:
                dp[i + j][j] = dp[i][j] + 1
                q.append((i + j, j))
        if i - 1 >= 0:
            if dp[i - 1][j] == -1:
                dp[i - 1][j] = dp[i][j] + 1
                q.append((i - 1, j))
        if i == S: break
    ans = sys.maxsize
    for y in dp[S]:
        if y != -1: ans = min(ans, y)
    print(ans)
