import sys
import math

if __name__ == '__main__':
    N = int(input())
    arr1 = list(map(int, sys.stdin.readline().split()))
    M = int(input())
    arr2 = list(map(int, sys.stdin.readline().split()))
    A, B = 1, 1
    for y in arr1: A *= y
    for y in arr2: B *= y
    print(str(math.gcd(A, B))[-9:])