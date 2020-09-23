def solution(arr, S, idx, tmp):
    global result
    if idx == len(arr):
        if tmp == S:
            result += 1
        return
    solution(arr, S, idx + 1, tmp + arr[idx])
    solution(arr, S, idx + 1, tmp)
if __name__== '__main__':
    N, S = list(map(int, input().split()))
    arr = list(map(int, input().split()))
    arr.sort()
    result = 0
    solution(arr, S, 0, 0)
    if S == 0:
        result -= 1
    print(result)