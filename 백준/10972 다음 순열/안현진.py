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
    if next_permutation(arr):
        sys.stdout.writelines(' '.join(map(str, arr)))
    else:
        sys.stdout.writelines(str(-1))