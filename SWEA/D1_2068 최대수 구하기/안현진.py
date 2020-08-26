T = int(input())
count = 0
while T > 0:
    T -= 1
    count += 1
    a = list(map(int, input().split()))
    tmp = a[0]
    for i in range(1,len(a)):
        if tmp < a[i]:
            tmp = a[i]
    print(f'#{count} {tmp}')