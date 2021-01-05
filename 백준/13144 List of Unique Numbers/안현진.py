import sys
from collections import deque

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    check = [False] * 100001 # 특정 원소 방문 여부 체크
    end, ans = 0, 0
    dp = [0] * N # 경우의 수 메모라이제이션
    q = deque() # 현재 수열

    # 투 포인터 활용
    for start in range(N):
        tmp = 0 # 현재 수열에서 새롭게 탐색한 원소의 개수
        flag = False
        # 오른쪽 방향으로 하나씩 탐색
        while end < N and not check[arr[end]]:
            flag = True
            check[arr[end]] = True # arr[end] 원소 방문
            q.append(arr[end]) # 현재 수열에 삽입
            end += 1 # 우측 인덱스 증가
            tmp += 1 # 새롭게 탐색한 원소 개수 증가
        # 우측 인덱스의 원소가 현재 수열에 있으면
        if not flag: dp[start] += (dp[start - 1] - 1) # start 일때의 경우의 수는 start - 1 일 때의 경우의 수 - 1
        else: # 우측 인덱스의 원소가 현재 수열에 없으면
            # start 일때의 경우의 수는 start - 1 일 때의 경우의 수 - 1 에다가 새롭게 탐색한 원소 개수(tmp) 더하기
            if start > 0: dp[start] += (dp[start - 1] - 1 + tmp)
            else: dp[start] += tmp # start 가 0일 경우에만 tmp 더해주기
        # 수열에서 좌측 인덱스 원소 빼주기
        x = q[0]
        check[x] = False
        q.popleft()
    for i in range(N):
        ans += dp[i]
    print(ans)