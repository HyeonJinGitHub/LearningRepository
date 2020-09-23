if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        arr = list(map(int, input().split()))
        t = arr[1] + arr[2]
        m, M = 0, 0
        if t < arr[0]:
            m = 0
            M = min(arr[1], arr[2])
        else:
            m = arr[1] + arr[2] - arr[0]
            M = min(arr[1], arr[2])
        print(f'#{i+1} {M} {m}')
