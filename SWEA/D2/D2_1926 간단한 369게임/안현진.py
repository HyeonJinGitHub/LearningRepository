N = int(input())
for i in range(1, N+1):
    tmp = str(i)
    t = ''
    for j in range(len(tmp)):
        if int(tmp[j]) != 0 and int(tmp[j]) % 3 == 0:
            t += '-'
    if t == '':
        print(i, end=' ')
    else:
        print(t, end=' ')