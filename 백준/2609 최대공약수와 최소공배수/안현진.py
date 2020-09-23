def gcd(a, b):
    while b != 0:
        r = a % b
        a = b
        b = r
    return a

if __name__ == '__main__':
    a, b = list(map(int, input().split()))
    print(gcd(a, b))
    print((a * b ) // gcd(a, b))
