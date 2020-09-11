if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        arr = [0]*5001
        res = []
        for j in range(N):
            a, b = list(map(int, input().split()))
            for k in range(a, b + 1):
                arr[k] += 1
        P = int(input())
        for j in range(P):
            t = int(input())
            res.append(arr[t])
        print(f'#{i+1}', end=' ')
        for j in range(len(res)):
            if not j == len(res) - 1:
                print(f'{res[j]}', end=' ')
            else:
                print(f'{res[j]}')
