T = int(input())
for i in range(T):
    arr = list(map(int, input().split()))
    arr.sort()
    s = 0
    for j in range(1, len(arr)-1):
        s += arr[j]
    print(f'#{i+1} {round(s / (len(arr)-2))}')