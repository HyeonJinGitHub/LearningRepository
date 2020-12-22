import sys

if __name__ == '__main__':
    N = int(input())
    M = int(input())
    check = [False] * 10
    if M:
        arr = list(map(int, sys.stdin.readline().split()))
        for y in arr:
            check[y] = True
    ans = abs(N - 100)
    for i in range(1000001):
        list_i = list(str(i))
        flag = False
        for y in list_i:
            if check[int(y)]:
                flag = True
                break
        if not flag:
            ans = min(ans, abs(N - i) + len(list_i))
    print(ans)
