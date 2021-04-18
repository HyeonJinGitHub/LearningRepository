from collections import defaultdict

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

blocks = {
    1: [2, 0, 3, 1],
    2: [2, 3, 1, 0],
    3: [1, 3, 0, 2],
    4: [3, 2, 0, 1],
    5: [2, 3, 0, 1]
}
def solution(x, y, d):
    tmp = 0
    while True:
        x += dx[d]
        y += dy[d]
        if x < 0 or x >= N or y < 0 or y >= N:
            tmp += 1
            d = (d + 2) % 4
            continue
        if not board[x][y]:
            if x == sx and y == sy:
                return tmp
            continue
        if board[x][y] == -1: return tmp
        if 1 <= board[x][y] <= 5:
            d = blocks[board[x][y]][d]
            tmp += 1
        elif 6 <= board[x][y] <= 10:
            (x1, y1), (x2, y2) = hall[board[x][y]][0], hall[board[x][y]][1]
            if x == x1 and y == y1:
                x, y = x2, y2
            elif x == x2 and y == y2:
                x, y = x1, y1

if __name__ == '__main__':
    for tc in range(int(input())):
        N = int(input())
        board = [list(map(int, input().split())) for _ in range(N)]
        hall = defaultdict(lambda :[])
        ans = 0
        for i in range(N):
            for j in range(N):
                if 6 <= board[i][j] <= 10:
                    hall[board[i][j]].append((i, j))
        for i in range(N):
            for j in range(N):
                if not board[i][j]:
                    sx, sy = i, j
                    for k in range(4):
                        ans = max(ans, solution(i, j, k))
        print('#{0} {1}'.format(tc + 1, ans))