import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(str, sys.stdin.readline().rstrip()))
    red = arr.count('R')
    blue = N - red

    # 더 적은 개수의 볼이 정답 후보
    ans = min(red, blue)
    cnt = 0
    # 앞에서부터 연속된 구간 확인하기
    for i in range(N):
        if arr[i] != arr[0]: break
        cnt += 1
    # 가장 첫번째 원소의 색상을 옮기는 것이 효율적
    if arr[0] == 'R': ans = min(ans, red - cnt)
    else: ans = min(ans, blue - cnt)

    cnt = 0
    # 뒤에서부터 연속된 구간 확인하기
    for i in range(N - 1, -1, -1):
        if arr[i] != arr[N - 1]: break
        cnt += 1
    # 가장 마지막 원소의 색상을 옮기는 것이 효율적
    if arr[N - 1] == 'R': ans = min(ans, red - cnt)
    else: ans = min(ans, blue - cnt)
    # 빨, 파 중 더 적은 개수, 가장 좌측 원소의 색상 기준으로 좌측으로 몰아넣기, 가장 우측 원소의 색상 기준으로 우측으로 몰아넣기 중 최소값이 정답
    print(ans)