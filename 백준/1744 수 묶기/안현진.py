if __name__ == '__main__':
    N = int(input())
    arr = []
    minus_cnt, plus_cnt = 0, 0
    zero_cnt = 0
    for _ in range(N):
        n = int(input())
        if n < 0: minus_cnt += 1
        elif n > 0: plus_cnt += 1
        else: zero_cnt += 1
        arr.append(n)
    arr.sort()
    ans = 0
    if minus_cnt % 2 == 0:
        for i in range(0, minus_cnt, 2):
            ans += (arr[i] * arr[i + 1])
    else:
        for i in range(0, minus_cnt - 1, 2):
            ans += (arr[i] * arr[i + 1])
        if not zero_cnt:
            ans += arr[minus_cnt - 1]
    zero_cnt -= 1
    idx = len(arr) - 1
    minus_cnt += (zero_cnt + 1)
    while idx >= minus_cnt:
        if not arr[idx]:
            idx -= 1
            continue
        if arr[idx] == 1:
            ans += 1
            idx -= 1
            continue
        if idx == minus_cnt:
            ans += arr[idx]
            break
        if arr[idx - 1] != 1:
            ans += (arr[idx] * arr[idx - 1])
        else:
            ans += arr[idx]
            idx -= 1
            continue
        idx -= 2
    print(ans)
