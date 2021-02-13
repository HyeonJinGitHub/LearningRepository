import sys

if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().rstrip()))
    q = []
    for y in arr:
        while q and q[-1] < y and K > 0:
            q.pop()
            K -= 1
        q.append(y)
    q = q[:-K] if K else q
    print(''.join(map(str, q)))