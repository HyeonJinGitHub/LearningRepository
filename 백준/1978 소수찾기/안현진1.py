import sys

if __name__ == '__main__':
    N = int(input())
    check = [False] * 1001
    for i in range(2, 1001):
        if not check[i]:
            for j in range(i * 2, 1001, i):
                check[j] = True
    ans = 0
    tmp = list(map(int, sys.stdin.readline().split()))
    for y in tmp:
        if y != 1:
            if not check[y]:
                ans += 1
    print(ans)