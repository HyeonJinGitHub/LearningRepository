if __name__ == '__main__':
    T = int(input())
    m = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    for i in range(T):
        arr = list(map(int, input().split()))
        if arr[2] == arr[0]:
            print(f'#{i+1} {arr[3] - arr[1] + 1}')
        else:
            tmp = 0
            for j in range(arr[0]+1, arr[2]):
                tmp += m[j - 1]
            tmp += (m[arr[0] - 1] - arr[1] + 1 + arr[3])
            print(f'#{i+1} {tmp}')