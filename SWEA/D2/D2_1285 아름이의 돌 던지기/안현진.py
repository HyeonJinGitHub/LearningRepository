if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        arr = list(map(int, input().split()))
        dis = 100001
        count = 0
        for j in range(len(arr)):
            tmp = abs(arr[j])
            if tmp < dis:
                dis = tmp
                count = 1
            elif tmp == dis:
                count += 1
        print(f'#{i+1} {dis} {count}')
