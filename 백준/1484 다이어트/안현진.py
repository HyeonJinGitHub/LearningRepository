if __name__== '__main__':
    G = int(input())
    P = [x for x in range(1, 100001)]
    Q = [x for x in range(1, 100001)]
    N, M = 100000, 100000
    left, right = 0, 0
    ans = []
    while left < N and right < M:
        tmp = (P[left] + Q[right]) * (P[left] - Q[right])
        if tmp == G: ans.append(P[left])
        if tmp < G:
            left += 1
            continue
        right += 1
    if not ans: print(-1)
    else:
        for y in ans: print(y)