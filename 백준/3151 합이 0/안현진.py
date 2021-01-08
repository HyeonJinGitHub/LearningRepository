import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    ans = 0
    # 한 개의 수 선택 후 투 포인터를 통해 나머지 두개의 수 구하기
    for i in range(N - 2):
        left, right = i + 1, N - 1
        goal = -arr[i]
        mx_idx = N
        while left < right:
            tmp = arr[left] + arr[right]
            if tmp < goal:
                left += 1
            elif tmp == goal: # 합이 0 인 경우
                # 정렬이 되어있기 때문에 left 원소와 right 원소가 같으면 둘 사이의 거리가 가능한 경우의 수
                if arr[left] == arr[right]:
                    ans += right - left
                else:
                    # left 원소와 right 원소가 서로 다른 경우
                    if mx_idx > right:
                        mx_idx = right
                        # right 에서 1씩 줄이면서 arr[right]와 몇개 있는지 확인
                        while mx_idx >= 0 and arr[mx_idx - 1] == arr[right]:
                            mx_idx -= 1
                    # arr[right]와 동일한 수 갯수 만큼 더하기
                    ans += right - mx_idx + 1
                left += 1
            else:
                right -= 1
    print(ans)