from itertools import permutations

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
    N = int(input())
    N_arr = list(map(int, input().split()))
    operator = list(map(int, input().split()))
    res_max, res_min = -1000000001, 1000000001
    cnts = []
    for i, cnt in enumerate(operator):
        for k in range(cnt):
            cnts.append(i)
    cnts.sort()
    cnt_arr = list(map(''.join, permutations(map(str, cnts))))
    for v in cnt_arr:
        tmp = N_arr[0]
        idx = 1
        for k in v:
            if k == '0':
                tmp += N_arr[idx]
            elif k == '1':
                tmp -= N_arr[idx]
            elif k == '2':
                tmp *= N_arr[idx]
            elif k == '3':
                if tmp >= 0:
                    tmp //= N_arr[idx]
                else:
                    tmp = -(-tmp // N_arr[idx])
            idx += 1
        res_max = max(res_max, tmp)
        res_min = min(res_min, tmp)
    # while True:
    #     tmp = N_arr[0]
    #     idx = 1
    #     for j in cnts:
    #         if j == '0':
    #             tmp += N_arr[idx]
    #         elif j == '1':
    #             tmp -= N_arr[idx]
    #         elif j == '2':
    #             tmp *= N_arr[idx]
    #         elif j == '3':
    #             if tmp >= 0:
    #                 tmp //= N_arr[idx]
    #             else:
    #                 tmp = -(-tmp // N_arr[idx])
    #         idx += 1
    #     res_max = max(res_max, tmp)
    #     res_min = min(res_min, tmp)
    #     if not next_permutation(cnts):
    #         break
    print(res_max)
    print(res_min)