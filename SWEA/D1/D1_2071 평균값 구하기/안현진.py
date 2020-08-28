def average(list_a):
    return (sum(list_a) / len(list_a))

T = int(input())
count = 0
while T > 0:
    T -= 1
    count += 1
    a = list(map(int, input().split()))
    print(f'#{count} {round(average(a))}')
