import sys

if __name__ == '__main__':
    for _ in range(int(input())):
        q = []
        N, M = map(int, sys.stdin.readline().split())
        n_list = list(map(int, sys.stdin.readline().split()))
        for idx, i in enumerate(n_list):
            q.append((i, idx))
        time = 1
        while q:
            flag = False
            value, idx = q.pop(0)
            if not q:
                print(time)
            for v, i in q:
                if v > value:
                    q.append((value, idx))
                    flag = False
                    break
                else:
                    flag = True
            if not flag: continue

            if idx == M:
                print(time)
                break
            time += 1
