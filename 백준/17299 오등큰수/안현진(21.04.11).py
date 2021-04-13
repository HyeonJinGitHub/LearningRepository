import sys

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, sys.stdin.readline().split()))
    cnt = dict()
    for y in arr:
        if y not in cnt:
            cnt[y] = 1
        else:
            cnt[y] += 1
    stack = []
    ans = [-1] * N

    for i in range(N - 1, -1, -1):
        if not stack:
            stack.append(arr[i])
            continue
        while stack and cnt[stack[-1]] <= cnt[arr[i]]:
            stack.pop()
        if stack:
            ans[i] = stack[-1]
        stack.append(arr[i])
    print(' '.join(map(str, ans)))