import sys
from itertools import combinations

if __name__ == '__main__':
    N, S = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    left = arr[:len(arr) // 2]
    right = arr[len(arr) // 2:]

    left_sum, right_sum = [], []
    for i in range(len(left) + 1):
        for y in combinations(left, i):
            left_sum.append(sum(y))
    for i in range(len(right) + 1):
        for y in combinations(right, i):
            right_sum.append(sum(y))
    left_sum.sort()
    right_sum.sort()
    ans = 0

    ls_size, rs_size = len(left_sum), len(right_sum)
    lp, rp = 0, rs_size - 1

    while lp < ls_size and rp >= 0:
        tmp = left_sum[lp] + right_sum[rp]

        if tmp == S:
            lt, rt = lp, rp
            lsame, rsame = 1, 1

            lp += 1
            while lp < ls_size and left_sum[lp] == left_sum[lt]:
                lp += 1
                lsame += 1
            rp -= 1
            while rp >= 0 and right_sum[rp] == right_sum[rt]:
                rp -= 1
                rsame += 1
            ans += (lsame * rsame)
        elif tmp < S:
            lp += 1
        else:
            rp -= 1
    print(ans - 1 if S == 0 else ans)