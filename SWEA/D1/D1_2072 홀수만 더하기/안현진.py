T = int(input())
count = 0
while T > 0:
    T -= 1
    count += 1
    sum = 0
    a = input().split()
    for i in range(0,len(a)):
        if int(a[i]) % 2 == 1:
            sum += int(a[i])
    print('#'+str(count)+' '+str(sum))