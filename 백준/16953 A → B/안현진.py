import sys

if __name__ == '__main__':
    A, B = map(int, sys.stdin.readline().split())
    cnt = 1
    while True:
        if B % 10 == 1:
            B //= 10
            cnt += 1
        elif B % 2 == 0:
            B //= 2
            cnt += 1
        else:
            cnt = -1
            break
        if B == A:
            break
        elif B < A:
            cnt = -1
            break
    print(cnt)