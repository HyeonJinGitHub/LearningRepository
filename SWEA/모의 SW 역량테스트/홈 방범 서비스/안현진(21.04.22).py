if __name__ == '__main__':
    for tc in range(int(input())):
        N, M = map(int, input().split())
        board = [list(map(int, input().split())) for _ in range(N)]
        house = []

        for i in range(N):
            for j in range(N):
                if board[i][j]:
                    house.append((i, j))
        ans = 1
        for k in range(2, N + 2):
            for i in range(N):
                for j in range(N):
                    cnt = 0
                    for (x, y) in house:
                        if abs(i - x) + abs(j - y) < k:
                            cnt += 1
                    if cnt > ans and cnt * M >= k ** 2 + (k - 1) ** 2:
                        ans = cnt
        print('#{} {}'.format(tc + 1, ans))