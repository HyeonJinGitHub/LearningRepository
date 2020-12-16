import sys

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    end, now, ans = 0, 0, 0

    for start in range(N):
        while now < M and end < N:
            now += arr[end]
            end += 1
        if now == M:
            ans += 1
        now -= arr[start]
    print(ans)