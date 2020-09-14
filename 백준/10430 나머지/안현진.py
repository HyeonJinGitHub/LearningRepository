if __name__ == '__main__':
    arr = list(map(int, input().split()))
    print((arr[0] + arr[1]) % arr[2])
    print(((arr[0] % arr[2]) + (arr[1] % arr[2])) % arr[2])
    print((arr[0] * arr[1]) % arr[2])
    print(((arr[0] % arr[2]) * (arr[1] % arr[2])) % arr[2])