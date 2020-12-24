import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    left, right = 0, len(arr) - 1
    x, y = left, right
    ans = arr[left] + arr[right]
    while left < right:
        tmp = arr[left] + arr[right]
        if abs(tmp) < abs(ans):
            ans = tmp
            x, y = left, right
            if tmp == 0:
                break
        if tmp < 0: left += 1
        else: right -= 1
    print(f'{arr[x]} {arr[y]}')