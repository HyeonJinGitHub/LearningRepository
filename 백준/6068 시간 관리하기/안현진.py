import sys

if __name__ == '__main__':
    N = int(input())
    arr = []
    for _ in range(N):
        a, b = map(int, sys.stdin.readline().split())
        arr.append((b, a))
    arr.sort(reverse=True)
    ans = arr[0][0] - arr[0][1]
    for i in range(1, N):
        if ans > arr[i][0]:
            ans = arr[i][0] - arr[i][1]
        else:
            ans -= arr[i][1]
    print(ans if ans >= 0 else -1)