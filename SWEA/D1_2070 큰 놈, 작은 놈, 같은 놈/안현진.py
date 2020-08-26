T = int(input())
count = 0
while T > 0:
    count += 1
    T -= 1
    a = list(map(int,input().split()))
    num1 = a[0]
    num2 = a[1]
    if num1 < num2:
        print(f'#{count} <')
    elif num1 > num2:
        print(f'#{count} >')
    else:
        print(f'#{count} =')
