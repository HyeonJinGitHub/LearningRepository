T = int(input())
for i in range(T):
    tc = int(input())
    arr = [[0 for col in range(tc)] for row in range(tc)]
    for j in range(tc):
        arr[j][0] = 1
        for k in range(1, j + 1):
            arr[j][k] = arr[j-1][k-1] + arr[j-1][k]
    print(f'#{i+1}')
    for j in range(tc):
        for k in range(0, j + 1):
            print(arr[j][k], end=' ')
        print()