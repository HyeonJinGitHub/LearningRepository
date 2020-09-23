if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        arr = list(map(int, input().split()))
        mean = sum(arr) / len(arr)
        count = 0
        for j in range(len(arr)):
            if arr[j] <= mean:
                count += 1
        print(f'#{i+1} {count}')
