import sys
from itertools import permutations

if __name__ == '__main__':
    N = int(sys.stdin.readline())
    arr = []
    for i in range(1, N + 1):
        arr.append(str(i))
    permute = list(map(' '.join, (permutations(arr))))
    for i in range(len(permute)):
        sys.stdout.writelines(permute[i] + '\n')