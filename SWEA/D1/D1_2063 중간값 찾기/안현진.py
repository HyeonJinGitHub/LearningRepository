N = int(input())
a = list(map(int, input().split()))
a.sort()
mid = len(a)//2
print(a[mid])