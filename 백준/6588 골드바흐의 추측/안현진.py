import sys

if __name__ == '__main__':
    arr = [False] * 1000001
    for i in range(2, 1000001):
        if not arr[i]:
            for j in range(i * 2, 1000001, i):
                arr[j] = True
    while True:
        n = int(sys.stdin.readline())
        ch = False
        if n == 0: break
        for i in range(2, n):
            if not arr[i] and not arr[n-i]:
                sys.stdout.write(f'{n} = {i} + {n-i}\n')
                ch = True
                break
        if not ch:
            sys.stdout.writeline("Goldbach's conjecture is wrong.\n")
        
