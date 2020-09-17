def solution(idx, s):
    global res_max, res_min
    if idx == len(arr) - 1:
        res_max = max(res_max, s)
        res_min = min(res_min, s)
        return
    if idx > len(arr) - 1:
        return
    if operator[0] > 0:
        operator[0] -= 1
        solution(idx + 1, s + arr[idx + 1])
        operator[0] += 1
    if operator[1] > 0:
        operator[1] -= 1
        solution(idx + 1, s - arr[idx + 1])
        operator[1] += 1
    if operator[2] > 0:
        operator[2] -= 1
        solution(idx + 1, s * arr[idx + 1])
        operator[2] += 1
    if operator[3] > 0:
        operator[3] -= 1
        if s < 0:
            solution(idx + 1, -(-s // arr[idx + 1]))
        else:
            solution(idx + 1, s // arr[idx + 1])
        operator[3] += 1

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))
    operator = list(map(int, input().split()))
    res_max = -1000000001
    res_min = 1000000001
    solution(0, arr[0])
    print(res_max)
    print(res_min)