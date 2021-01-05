import sys
from collections import deque

if __name__ == '__main__':
    N = int(input())
    S = list(map(str, sys.stdin.readline().rstrip()))
    check = [0] * 26
    size = len(S)
    left, right = 0, 0
    ans, tmp = 0, 0
    q = deque()

    while left < size and right < size:
        while right < size:
            if tmp == N and not check[ord(S[right]) - ord('a')]:
                break
            if not check[ord(S[right]) - ord('a')]:
                tmp += 1
            check[ord(S[right]) - ord('a')] += 1
            q.append(S[right])
            right += 1

        ans = max(ans, len(q))
        first = q[0]
        check[ord(first) - ord('a')] -= 1
        q.popleft()
        if not check[ord(first) - ord('a')]:
            tmp -= 1
        left += 1
    print(ans)