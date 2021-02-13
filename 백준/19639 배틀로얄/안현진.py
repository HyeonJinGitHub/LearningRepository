import sys

if __name__ == '__main__':
    X, Y, M = map(int, sys.stdin.readline().split())
    damage, heal = [], []
    for _ in range(X): damage.append(int(sys.stdin.readline()))
    for _ in range(Y): heal.append(int(sys.stdin.readline()))

    if M + sum(heal) <= sum(damage):
        print(0)
        exit(0)
    ans = []
    max_M = M
    d_idx, h_idx = 0, 0

    for _ in range(X + Y):
        if M <= max_M // 2 and len(heal) > 0:
            M += heal[h_idx]
            h_idx += 1
            ans.append(h_idx)
        elif M > max_M // 2 and len(damage) > 0:
            M -= damage[d_idx]
            d_idx += 1
            ans.append(-d_idx)

        if d_idx == len(damage) and h_idx != len(heal):
            while h_idx < len(heal):
                M += heal[h_idx]
                h_idx += 1
                ans.append(h_idx)
            break
    print('\n'.join(map(str, ans)) if len(ans) == X + Y else 0)