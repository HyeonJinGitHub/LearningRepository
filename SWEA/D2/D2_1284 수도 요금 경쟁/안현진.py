if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        arr = list(map(int, input().split()))
        res1 = arr[0]*arr[4]
        res2 = 0
        if arr[4] <= arr[2]:
            res2 = arr[1]
        else:
            res2 = arr[1] + (arr[4] - arr[2])*arr[3]
        if res1 < res2:
            res = res1
        else:
            res = res2
        print(f'#{i+1} {res}')