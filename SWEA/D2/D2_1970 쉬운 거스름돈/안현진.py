def solution(N):
    money = [50000, 10000, 5000, 1000, 500, 100, 50, 10]
    res = [0, 0, 0, 0, 0, 0, 0, 0]
    for i in range(len(money)):
        if N // money[i] > 0:
            res[i] = (N // money[i])
            N -= money[i]*(N // money[i])
    return res

if __name__ == "__main__":
    T = int(input())
    for i in range(T):
        N = int(input())
        res = solution(N)
        print(f'#{i+1}')
        for j in range(len(res)):
            print(f'{res[j]}', end=' ')
        print()