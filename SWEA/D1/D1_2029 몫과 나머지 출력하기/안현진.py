T = int(input())
count = 0
while T > 0:
    T -= 1
    count += 1
    a, b = list(map(int, input().split()))
    print(f'#{count} {a//b} {a%b}')