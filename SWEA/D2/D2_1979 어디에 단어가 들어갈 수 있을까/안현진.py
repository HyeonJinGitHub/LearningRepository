T = int(input())
for i in range(T):
    N, K = list(map(int, input().split()))
    arr = [[int(x) for x in input().split()] for y in range(N)]
    res = 0
    for x in range(N):
        tmp = 0
        for y in range(N):
            if arr[x][y] == 1:
                tmp += 1
            else:
                if tmp == K:
                    res += 1
                tmp = 0
        if tmp == K:
            res += 1
    for y in range(N):
        tmp = 0
        for x in range(N):
            if arr[x][y] == 1:
                tmp += 1
            else:
                if tmp == K:
                    res += 1
                tmp = 0
        if tmp == K:
            res += 1
    print(f'#{i+1} {res}')
