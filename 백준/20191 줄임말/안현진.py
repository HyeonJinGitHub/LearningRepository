import sys
from bisect import bisect_left, bisect_right

if __name__ == '__main__':
    S = list(map(str, sys.stdin.readline().rstrip()))
    T = list(map(str, sys.stdin.readline().rstrip()))
    word = [[] for _ in range(26)]
    cnt = 0
    for y in T:
        word[ord(y) - ord('a')].append(cnt)
        cnt += 1
    flag = True
    ans = 1
    pos, beforePos = -1, -1
    for y in S:
        k = ord(y) - ord('a')
        if not len(word[k]):
            flag = False
            break
        pos = bisect_right(word[k], beforePos)
        if pos == len(word[k]):
            ans += 1
            pos = 0
        beforePos = word[k][pos]
    print(-1 if not flag else ans)