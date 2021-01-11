import sys

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    money = []
    for _ in range(N):
        money.append(int(input()))
    money.sort(reverse=True)
    ans = 0
    for m in money:
        if not K // m: continue
        ans += K // m
        K %= m
        if not K: break
    print(ans)