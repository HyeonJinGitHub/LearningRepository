import sys

if __name__ == '__main__':
    N = int(input())
    ans, i = 0, 1
    while i <= N:
        ans += (N - i + 1)
        i *= 10
    print(ans)