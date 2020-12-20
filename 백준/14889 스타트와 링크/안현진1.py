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
        j -= 1
        i += 1
    return True

if __name__ == '__main__':
    N = int(input())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    check = [False] * N
    for i in range(N // 2):
        check[i] = True
    tmp = [x for x in check]
    check.sort()
    ans = sys.maxsize

    while True:
        t1, t2 = 0, 0
        false_list, true_list = [], []
        for i in range(N):
            if check[i]: true_list.append(i)
            else: false_list.append(i)
        for i in range(N // 2 - 1):
            for j in range(i + 1, N // 2):
                t1 += board[true_list[i]][true_list[j]] + board[true_list[j]][true_list[i]]
                t2 += board[false_list[i]][false_list[j]] + board[false_list[j]][false_list[i]]
        ans = min(ans, abs(t1 - t2))
        if tmp == check:
            break
        next_permutation(check)
    print(ans)