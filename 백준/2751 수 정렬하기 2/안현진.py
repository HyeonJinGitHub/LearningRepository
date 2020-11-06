import sys

if __name__ == '__main__':
    N = int(input())
    t = []
    for _ in range(N):
        a = int(sys.stdin.readline())
        t.append(a)
    t.sort()
    for i in t:
        print(i)