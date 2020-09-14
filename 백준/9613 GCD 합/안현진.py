def gcd(a, b):
    while b != 0:
        r = a % b
        a = b
        b = r
    return a
if __name__ == '__main__':
    for t in range(int(input())):
        arr = list(map(int, input().split()))
        res = 0
        for i in range(1, len(arr)):
            for j in range(i + 1, len(arr)):
                res += gcd(arr[i], arr[j])
        print(res)