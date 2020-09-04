a, b = list(map(int, input().split()))
res = 0
while b <= a:
    res += 1
    b += 1
print(res)