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
    a = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    check = [False] * N
    init = [x for x in check]
    res = 1000000
    for i in range(N // 2):
        check[i] = True
        init[i] = True
    check.sort()

    while True:
        first, second = [], []
        for i in range(N):
            if not check[i]: first.append(i)
            else: second.append(i)
        sum_f, sum_s = 0, 0
        for i in range(N // 2):
            for j in range(N // 2):
                if i == j: continue
                sum_f += a[first[i]][first[j]]
                sum_s += a[second[i]][second[j]]
        diff = abs(sum_s - sum_f)
        res = min(res, diff)
        if check == init:
            break
        next_permutation(check)
    print(res)
