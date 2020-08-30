T = int(input())
for i in range(T):
    s = input()
    tmp = s[::-1]
    if s == tmp:
        print(f'#{i+1} 1')
    else:
        print(f'#{i+1} 0')