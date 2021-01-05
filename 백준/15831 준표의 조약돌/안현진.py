import sys
from collections import deque

if __name__ == '__main__':
    N, B, W = map(int, sys.stdin.readline().split())
    arr = list(map(str, sys.stdin.readline().rstrip()))

    left, right = 0, 0
    w_cnt, b_cnt = 0, 0
    q = deque()
    ans = 0
    while left < N and right < N:
        while right < N and b_cnt <= B:
            if b_cnt == B and arr[right] == 'B': break
            if arr[right] == 'B': b_cnt += 1
            else: w_cnt += 1
            q.append(arr[right])
            right += 1
        if b_cnt <= B and w_cnt >= W:
            ans = max(ans, len(q))
        if not q: # B 가 0 이고 BBBBB 와 같이 B가 연속적으로 들어오는 예외 상황 처리
            left += 1
            right += 1
            continue
        x = q[0]
        if x == 'B': b_cnt -= 1
        else: w_cnt -= 1
        q.popleft()
        left += 1
    print(ans)