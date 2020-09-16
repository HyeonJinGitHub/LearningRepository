import sys

def next_permutation(a):
    i = len(a) - 1
    while i > 0 and a[i-1] >= a[i]:
        i -= 1
    if i <= 0:
        return False
    j = len(a) - 1

    while a[i-1] >= a[j]:
        j -= 1

    a[i-1], a[j] = a[j], a[i-1]
    j = len(a) - 1
    while i < j:
        a[i], a[j] = a[j], a[i]
        i += 1
        j -= 1
    return True

if __name__ == '__main__':
    N = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    res = 0
    tmp = 0
    while True:
        for i in range(1, len(arr)):
            tmp += abs(arr[i] - arr[i-1])
        if res < tmp: res = tmp
        if not next_permutation(arr):
            break
        tmp = 0
    print(res)