def solution(day, s):
    global res
    if day == N + 1:
        res = max(res, s)
        return
    if day > N + 1:
        return
    solution(day + 1, s)
    solution(day + T[day], s+P[day])
if __name__ == '__main__':
    N = int(input())
    T = [0] * (N + 1)
    P = [0] * (N + 1)
    for i in range(1, N + 1):
        T[i], P[i] = map(int, input().split())
    res = 0
    solution(1, 0)
    print(res)