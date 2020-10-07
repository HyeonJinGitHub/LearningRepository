import sys
from collections import Counter

if __name__ == '__main__':
    N = int(sys.stdin.readline())
    arr = [int(x) for x in sys.stdin.readline().split()]
    M = int(sys.stdin.readline())
    b = [int(x) for x in sys.stdin.readline().split()]
    arr = Counter(arr)
    for i in b: print(arr[i], end=' ')