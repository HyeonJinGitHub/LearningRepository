T = int(input())
for i in range(T):
    N, M = list(map(int, input().split()))
    arr = [[int(x) for x in input().split()] for y in range(N)]
    sum_max = 0
    x = y = 0
    for j in range(N-M+1):
        for k in range(N-M+1):
            tmp = 0
            for l in range(M):
                for m in range(M):
                    tmp += arr[j+l][k+m]
            if sum_max < tmp:
                sum_max = tmp
    print(f'#{i+1} {sum_max}')
