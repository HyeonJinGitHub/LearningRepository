import sys

MOD = 1000000007

if __name__ == '__main__':
    for _ in range(int(input())):
        N = int(input())
        arr = list(map(int, sys.stdin.readline().split()))
        mx, mn = -3, 3
        for y in arr:
            mx = max(mx, y)
            mn = min(mn, y)
        if mx == 1 and mn == 1: print(1)
        elif mx == 1 and mn == 0: print(1)
        elif mx == 0 and mn == 0: print(0)
        elif mx == -1 and mn == -1: print(1)
        elif mx == 1 and mn == -1: print(1)
        elif mx == 0 and mn == -1:
            now_minus = 0
            flag = False
            for i in range(N):
                if not arr[i]:
                    now_minus = 0
                if arr[i] == -1:
                    now_minus += 1
                if now_minus % 2 == 0 and now_minus != 0:
                    print(1)
                    flag = True
                    break
            if not flag: print(0)
        else:
            now_two, now_minus = 0, 0
            ans_two, ans = 0, max(arr)
            for i in range(N):
                if not arr[i]:
                    now_two, now_minus = 0, 0
                    continue
                if arr[i] < 0:
                    now_minus += 1
                    if arr[i] == -2: now_two += 1
                else:
                    if arr[i] == 2:
                        now_two += 1
                if now_minus % 2 == 0:
                    ans_two = max(ans_two, now_two)
                    ans = 1
                    now_minus = 0
            now_two, now_minus = 0, 0
            for i in range(N - 1, -1, -1):
                if not arr[i]:
                    now_two, now_minus = 0, 0
                    continue
                if arr[i] < 0:
                    now_minus += 1
                    if arr[i] == -2: now_two += 1
                else:
                    if arr[i] == 2:
                        now_two += 1
                if now_minus % 2 == 0:
                    ans_two = max(ans_two, now_two)
                    ans = 1
                    now_minus = 0
            if ans_two != 0:
                ans = 1
                while ans_two:
                    ans = (ans * 2) % MOD
                    ans_two -= 1
            print(ans)