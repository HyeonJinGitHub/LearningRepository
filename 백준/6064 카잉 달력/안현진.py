import sys

if __name__ == '__main__':
    for _ in range(int(input())):
        M, N, x, y = map(int, sys.stdin.readline().split())
        if M > N:
            M, N = N, M
            x, y = y, x
        dist = N - M
        sx, sy = x, x
        flag = False
        ans = 0
        for i in range(N):
            if sx == x and sy == y:
                flag = True
                break
            sy -= dist
            if sy <= 0: sy += N
            ans += M
        ans += x
        if flag: print(ans)
        else: print(-1)