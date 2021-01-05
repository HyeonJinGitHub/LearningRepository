import sys

# did you read the statement
# did you actually read the statement
# are there new samples to copy
# did you test on the new samples
# slow/fast?
# are your mappings clear?
# should you think about your implementation?
# did you restart your editor right beforehand
# did you copy the input properly

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    ans = sys.maxsize
    for i in range(N):
        for j in range(i + 3, N):
            left, right = i + 1, j - 1
            while left < right:
                tmp = (arr[i] + arr[j]) - (arr[left] + arr[right])
                if abs(ans) > abs(tmp):
                    ans = abs(tmp)
                # 정렬이 되어 있기 때문에, tmp 가 0보다 작은 경우는(arr[left] + arr[right] > arr[i] + arr[j])
                # tmp 를 늘릴 필요가 있으므로 right 를 감소
                if tmp < 0: right -= 1
                # 정렬이 되어 있기 때문에, tmp 가 0보다 큰 경우는(arr[left] + arr[right] < arr[i] + arr[j])
                # tmp 를 줄일 필요가 있으므로 left 를 증가
                else: left += 1
    print(ans)

