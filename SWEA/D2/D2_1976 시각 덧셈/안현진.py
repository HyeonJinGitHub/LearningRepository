T = int(input())
for i in range(T):
    arr = list(map(int, input().split()))
    h = arr[0] + arr[2]
    m = arr[1] + arr[3]
    if h > 12:
        h -= 12
    if m > 59:
        h += 1
        m -= 60
    print(f'#{i+1} {h} {m}')