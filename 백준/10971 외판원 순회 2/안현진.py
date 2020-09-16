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
    arr = [[int(x) for x in sys.stdin.readline().split()] for y in range(N)]
    tmp1 = list(range(N))
    res = 10000001
    while True:
        tmp = 0
        check = False
        for i in range(N - 1):
            if arr[tmp1[i]][tmp1[i+1]] == 0:
                check = True
                break
            else:
                tmp += arr[tmp1[i]][tmp1[i+1]]
        if not check and arr[tmp1[N - 1]][tmp1[0]] != 0:
            tmp += arr[tmp1[N-1]][tmp1[0]]
            if tmp < res:
                res = tmp
        if not next_permutation(tmp1):
            break
        if tmp1[0] != 0:
            break
    sys.stdout.writelines(str(res))
