T = int(input())
for i in range(T):
    N = int(input())
    tmp = 0
    for j in range(1, N+1):
        if j % 2 == 0:
            tmp -= j
        else:
            tmp += j
    print(f'#{i+1} {tmp}')