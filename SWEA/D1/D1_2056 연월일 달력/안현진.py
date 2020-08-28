T = int(input())
count = 0
while T > 0:
    T -= 1
    count += 1
    M_31 = (1,3,5,7,8,10,12,)
    M_30 = (4,6,9,11,)
    a = input()
    month = int(a[4:6])
    day = int(a[6:])
    if month < 1 or month > 12:
        print(f'#{count} -1')
    else:
        if month in M_31:
            if day > 31 or day < 1:
                print(f'#{count} -1')
            else:
                print(f'#{count} {a[:4]}/{a[4:6]}/{a[6:]}')
        elif month in M_30:
            if day > 30 or day < 1:
                print(f'#{count} -1')
            else:
                print(f'#{count} {a[:4]}/{a[4:6]}/{a[6:]}')
        else:
            if day > 28 or day < 1:
                print(f'#{count} -1')
            else:
                print(f'#{count} {a[:4]}/{a[4:6]}/{a[6:]}')