# from itertools import combinations
# import sys
#
# if __name__ == '__main__':
#     while True:
#         arr = list(map(str, sys.stdin.readline().split()))
#         N = int(arr[0])
#         del arr[0]
#         if N == 0:
#             break
#         res = list(map(' '.join, combinations(arr, 6)))
#         for i in range(len(res)):
#             sys.stdout.writelines(''.join(res[i]) + '\n')
#         print()


# import sys
#
# def next_permutation(a):
#     i = len(a) - 1
#     while i > 0 and a[i-1] >= a[i]:
#         i -= 1
#     if i <=0:
#         return False
#     j = len(a) - 1
#     while a[i-1] >= a[j]:
#         j -= 1
#     a[i-1], a[j] = a[j], a[i - 1]
#     j = len(a) - 1
#     while i < j:
#         a[i], a[j] = a[j], a[i]
#         i += 1
#         j -= 1
#     return True
#
# if __name__ == '__main__':
#     while True:
#         N, *arr = list(map(int, sys.stdin.readline().split()))
#         if N == 0:
#             break
#         d = [0] * (N-6) + [1] * 6
#         res = []
#         while True:
#             cur = [arr[i] for i in range(N) if d[i] == 1]
#             res.append(cur)
#             if not next_permutation(d):
#                 break
#         res.sort()
#         for v in res:
#             sys.stdout.writelines(' '.join(map(str, v)) + '\n')
#         print()

def solution(idx, arr, lotto):
    if len(lotto) == 6:
        print(' '.join(map(str, lotto)))
        return
    if idx == len(arr):
        return
    solution(idx + 1, arr, lotto+[arr[idx]])
    solution(idx + 1, arr, lotto)
if __name__ == '__main__':
    while True:
        N, *arr = list(map(int, input().split()))
        if N == 0:
            break
        solution(0, arr, [])
        print()