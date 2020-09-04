
if __name__ == "__main__":
    T = int(input())
    for i in range(T):
        N, M = list(map(int, input().split()))
        arr1 = list(map(int, input().split()))
        arr2 = list(map(int, input().split()))
        res = 0
        t = 0
        if N < M:
            while t < M-N+1:
                tmp = 0
                for j in range(N):
                    tmp += arr1[j]*arr2[t+j]
                if tmp > res:
                    res = tmp
                t += 1
        elif N > M:
            while t < N-M+1:
                tmp = 0
                for j in range(M):
                    tmp += arr1[t+j]*arr2[j]
                if tmp > res:
                    res = tmp
                t += 1
        else:
            tmp = 0
            for j in range(N):
                tmp += arr1[j]*arr2[j]
            if tmp > res:
                tmp = res
        print(f'#{i+1} {res}')