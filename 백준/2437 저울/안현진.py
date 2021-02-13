import sys

if __name__ == '__main__':
    N = int(input())
    arr = sorted(list(map(int, sys.stdin.readline().split())))
    now = 1
    for y in arr:
        if now < y:
            break
        now += y
    print(now)