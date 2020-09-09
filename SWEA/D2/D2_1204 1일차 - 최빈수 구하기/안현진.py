if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        tn = int(input())
        arr = list(map(int, input().split()))
        cnt = [0]*101
        for j in range(len(arr)):
            cnt[arr[j]] += 1
        tmp = cnt[0]
        res = 0
        for j in range(1, len(cnt)):
            if tmp <= cnt[j]:
                tmp = cnt[j]
                res = j
        print(f'#{i+1} {res}')