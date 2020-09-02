if __name__ == "__main__":
    T = int(input())
    for i in range(T):
        N = int(input())
        arr = list(map(int, input().split()))
        arr.sort()
        print(f'#{i+1}', end=' ')
        for j in range(len(arr)):
            print(f'{arr[j]}', end=' ')
        print()