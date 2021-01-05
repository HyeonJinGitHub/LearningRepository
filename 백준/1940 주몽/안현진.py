import sys

if __name__ == '__main__':
    N = int(input())
    M = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    ans = 0
    left, right = 0, N - 1
    while left < right:
        tmp = arr[left] + arr[right]
        if tmp == M: ans += 1
        if tmp < M: left += 1
        else: right -= 1
    print(ans)