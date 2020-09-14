if __name__ == '__main__':
    res = 0
    ch = [False]*1001
    for i in range(2, 1001):
        if not ch[i]:
            for j in range(i*2, 1001, i):
                ch[j] = True
    N = int(input())
    arr = list(map(int, input().split()))
    for i in range(len(arr)):
        if not ch[arr[i]] and arr[i] > 1:
            res += 1
    print(res)
