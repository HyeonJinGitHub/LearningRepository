import sys

def divide(x, y, d1, d2, ans): # 기준점을 5번영역의 가장 상단에 두고 구역 나누기 =
    while True:
        while True:
            lx, ly, rx, ry = x + d1, y - d1, x + d2, y + d2 # 순서대로 기준점 중심으로 좌측하단, 우측하단
            if rx == n-1 or ry == n: # 우측하단의 좌표에서 높이가 가장 높은 n-1 이거나 가로가 n인경우 즉 조건에서 불가능한 경우
                break #정지
            bx, by = x + d1 + d2, y - d1 + d2 # 가장 하단 구역의 좌표 계산
            if bx >= n or by >= n or by < 0: # 가장 하단의 좌표가 배열 범위를 벗어나는 경우
                break#중지
            ans = min(ans, find_min(x, y, lx, ly, rx, ry, by)) #인구 차이의 최소값 구하기
            d2 += 1 # 우측 증가
        #우측 끝까지 갔으면 좌측 증가
        d1 += 1
        if x + d1 == n-1 or y - d1 == -1: # 가장 상단의 위치가 갈 수 있는 범위를 넘은 경우?
            break# 중지
        d2 = 1# 우측 초기화

    return ans # 모든 영역에서의 인구 차이 최소값 반환

def find_min(x, y, lx, ly, rx, ry, by):#최소값 구하기
    cnt1, cnt2, cnt3, cnt4, d = 0, 0, 0, 0, 0 # 영역별 넓이 구할 변수, d는 좌 우측으로 범위 점점 증가하는 것 표현
    #1번 영역 구하기
    for i in range(lx): # 기준점 부터 좌측 끝까지
        for j in range(y+1):# 기준점 기준으로 위에까지 확인 하기 위해 Y+1까지
            if [i, j] == [x + d, y - d]: # 좌측에 있는 5구역의 경계
                d += 1 # D 1씩 증가
                break # 중지
            cnt1 += a[i][j] # 1번 영역의 넓이 합 구하기
    # 2번 영역 구하기
    d = 1
    for i in range(rx+1):# 2번 영역은 5번 영역의 최우측과 같은 높이 까지 가능
        for j in range(n-1, y, -1):# 뒤에서부터 계산 => 우측 상단부터 계산
            if [i, j] == [x + d, y + d]: # 5번 구역 안에 들어갈 경우
                d += 1 # d 증가
                break#중지
            cnt2 += a[i][j] # 2번 영역의 합 구하기
    # 3번 영역 구하기
    d = 0
    for i in range(lx, n): # 5번영역의 좌측끝부터 3번 영역의 시작 ~ n-1 에 끝
        for j in range(by): # 5번 영역의 가장 하단 y좌표 전까지
            if [i, j] == [lx + d, ly + d]: # 5번 영역의 안에 들어갈 경우
                d += 1 # d 증가
                break # 중지
            cnt3 += a[i][j] # 3번 영역의 합 구하기
    # 4번 영역 궇가ㅣ
    d = 1
    for i in range(rx+1, n): # 우측 상단x좌표보다 1 큰 x좌표부터 4번 영역 시작
        for j in range(n-1, by-1, -1): # 뒤에서부터 계산, 5번영역의 가장 좌측부터 시작하면 가장 좌측 안쪽의 4번 영역을 구할 수 없음, 그래서 뒤에서부터 시작
            if [i, j] == [rx + d, ry - d]: # 5번 영역 안에 들어온 경우
                d += 1 # d 증가
                break
            cnt4 += a[i][j]#4번 영역 구하기

    cnt5 = nsum - cnt1 - cnt2 - cnt3 - cnt4 # 5번 영역은 전체 영역의 합에서 1~4영역을 뺴준것
    max_cnt = max(cnt1, cnt2, cnt3, cnt4, cnt5) # 각 영영에서 최대값 계산
    min_cnt = min(cnt1, cnt2, cnt3, cnt4, cnt5) # 각 영역에서 최소값 계산
    return max_cnt - min_cnt # 최대와 최소 차이 리턴

if __name__ == '__main__':
    n = int(input())

    a, nsum = [], 0
    for _ in range(n):
        row = list(map(int, sys.stdin.readline().split()))
        nsum += sum(row)
        a.append(row)

    ans = sys.maxsize
    # i, j 는 가장 상단에 위치한 구역의 기준점
    for i in range(n-2): # 세로 : 0부터 n-3 까지 가능
        for j in range(1, n-1): # 가로 : 1부터 n-2 까지 가능
            d1, d2 = 1, 1 # d1, d2 각각 1부터 시작
            ans = divide(i, j, d1, d2, ans) # 영역 나누기 시작
    print(ans)