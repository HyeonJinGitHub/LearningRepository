if __name__ == '__main__':
    for tc in range(int(input())):
        N, K = map(int, input().split())
        arr = input()
        ans = set()
        for _ in range(N):
            size = N // 4
            for i in range(0, N, size):
                tmp = arr[i:i+size]
                ans.add(int(tmp, 16))
            arr = arr[-1] + arr[:-1]
        ans = sorted(list(ans), reverse=True)
        print(f'#{tc + 1} {ans[K - 1]}')



