import sys

if __name__ == '__main__':
    l, w, h = map(int, sys.stdin.readline().split())
    N = int(input())
    cube = [0] * 21
    ans = 0
    for _ in range(N):
        x, y = map(int, sys.stdin.readline().split())
        cube[x] += y
    total = 0
    for i in range(19, -1, -1):
        total <<= 3
        t = min(cube[i], (l >> i) * (w >> i) * (h >> i) - total)
        total += t
        ans += t
    print(ans if total == l * w * h else -1)