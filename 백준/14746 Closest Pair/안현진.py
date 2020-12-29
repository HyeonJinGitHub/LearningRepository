import sys

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    c1, c2 = map(int, sys.stdin.readline().split())
    P = list(map(int, sys.stdin.readline().split()))
    Q = list(map(int, sys.stdin.readline().split()))
    P.sort(); Q.sort()

    right = 0
    ans = sys.maxsize
    cnt = 0

    for left in range(N):
        while right < M and P[left] >= Q[right]:
            ans = min(ans, abs(P[left] - Q[right]))
            right += 1
        if right < M and P[left] <= Q[right]:
            ans = min(ans, abs(P[left] - Q[right]))
    right = 0
    for left in range(N):
        while right < M and P[left] >= Q[right]:
            cnt += 1 if ans == abs(P[left] - Q[right]) else 0
            right += 1
        if right < M and P[left] <= Q[right]:
            cnt += 1 if ans == abs(P[left] - Q[right]) else 0
    print(ans + abs(c1 - c2), cnt)