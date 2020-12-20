import sys

def gcd(a, b):
    if b == 0: return a
    else: return gcd(b, a % b)

if __name__ == '__main__':
    for _ in range(int(input())):
        tmp = 0
        arr = list(map(int, sys.stdin.readline().split()))[1:]
        for i in range(len(arr)):
            for j in range(i + 1, len(arr)):
                tmp += gcd(arr[i], arr[j])
        print(tmp)