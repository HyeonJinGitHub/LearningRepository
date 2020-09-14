def gcd(a, b):
    while b != 0:
        r = a % b
        a = b
        b = r
    return a
if __name__ == '__main__':
    for i in range(int(input())):
        A, B = list(map(int, input().split()))
        print((A*B) // gcd(A, B))