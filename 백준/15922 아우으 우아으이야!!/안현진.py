import sys

if __name__ == '__main__':
    N = int(input())
    start, end = -1000000001, -1000000001
    ans = 0
    for i in range(N):
        s, e = map(int, sys.stdin.readline().split())
        if not i:
            start, end = s, e
            continue
        if s > end:
            ans += (end - start)
            start, end = s, e
        elif e > end:
            end = e
    ans += (end - start)
    print(ans)
