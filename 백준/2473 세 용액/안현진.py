import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()

    x, y, z = 0, 0, 0
    ans = sys.maxsize

    for i in range(N - 2):
        start, end = i + 1, N - 1
        while start < end:
            tmp = arr[i] + arr[start] + arr[end]
            if abs(tmp) < ans:
                ans = abs(tmp)
                x, y, z= i, start, end
            if tmp < 0: start += 1
            else: end -= 1
    print(f'{arr[x]} {arr[y]} {arr[z]}')