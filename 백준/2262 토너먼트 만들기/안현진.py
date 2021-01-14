import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    ans = 0
    # arr 리스트에서 가장 큰 수(mx)부터 선택해서 해당 수의 좌우를 비교하여 둘 중 더 큰 수와의 차이를 결과에 더해주고,
    # 처음에 선택한 수(mx)를 arr 에서 제거한다.
    # 이 과정을 arr 리스트의 개수가 1일 때까지 반복한다.
    while True:
        if len(arr) == 1:
            break
        mx = max(arr)
        mx_idx = arr.index(mx)
        if 0 < mx_idx < len(arr) - 1:
            a, b = arr[mx_idx - 1], arr[mx_idx + 1]
            tmp = max(a, b)
        elif mx_idx == 0:
            tmp = arr[mx_idx + 1]
        elif mx_idx == len(arr) - 1:
            tmp = arr[mx_idx - 1]
        ans += (mx - tmp)
        arr.pop(mx_idx)
    print(ans)

