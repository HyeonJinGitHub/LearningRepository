import sys
if __name__ == '__main__':
    N = int(input())
    for _ in range(N):
        a, b = map(str, sys.stdin.readline().split())
        b = int(b)
        t = int(a[-1])
        if t == 0: print(10)
        else:
            tmp = []
            for i in range(1, b + 1):
                if str(t ** i)[-1] not in tmp:
                    tmp.append(str(t ** i)[-1])
                else: break
            if len(tmp) == 1:
                print(tmp[0])
                continue
            if len(tmp) < b:
                b = (b % len(tmp)) - 1
            else:
                b -= 1
            print(tmp[b])
