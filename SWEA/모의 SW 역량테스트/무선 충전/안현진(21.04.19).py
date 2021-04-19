from collections import deque
from collections import defaultdict

dx = [0, -1, 0, 1, 0]
dy = [0, 0, 1, 0, -1]
if __name__ == '__main__':
    for tc in range(int(input())):
        board = defaultdict(lambda :[])
        M, A = map(int, input().split())
        bc_arr = [0] * (A + 1)
        n1 = list(map(int, input().split()))
        n2 = list(map(int, input().split()))
        x1, y1, x2, y2 = 0, 0, 9, 9
        sum1, sum2 = 0, 0

        for i in range(A):
            y, x, c, p = map(int, input().split())
            q = deque([(x - 1, y - 1)])
            dist = [[-1] * 10 for _ in range(10)]
            board[(x - 1, y - 1)].append(i + 1)
            bc_arr[i + 1] = p
            dist[x - 1][y - 1] = 0
            while q:
                x, y = q.popleft()
                for k in range(1, 5):
                    nx, ny = x + dx[k], y + dy[k]
                    if 0 <= nx < 10 and 0 <= ny < 10:
                        if dist[nx][ny] == -1:
                            if dist[x][y] + 1 <= c:
                                dist[nx][ny] = dist[x][y] + 1
                                q.append((nx, ny))
                                board[(nx, ny)].append(i + 1)
        for i in range(M + 1):
            p1_arr = board[(x1, y1)]
            p2_arr = board[(x2, y2)]
            if not p1_arr and not p2_arr:
                if i != M:
                    x1 += dx[n1[i]]; y1 += dy[n1[i]]
                    x2 += dx[n2[i]]; y2 += dy[n2[i]]
                continue
            intersection = list(set(p1_arr) & set(p2_arr))
            if not intersection:
                tmp1, tmp2 = [], []
                for p1 in p1_arr:
                    tmp1.append(bc_arr[p1])
                for p2 in p2_arr:
                    tmp2.append(bc_arr[p2])
                if tmp1:
                    sum1 += max(tmp1)
                if tmp2:
                    sum2 += max(tmp2)
                if i != M:
                    x1 += dx[n1[i]]; y1 += dy[n1[i]]
                    x2 += dx[n2[i]]; y2 += dy[n2[i]]
                continue
            tmp_sum1, tmp_sum2 = 0, 0
            tmp_sum = 0
            for p1 in p1_arr:
                for p2 in p2_arr:
                    if p1 == p2:
                        if (bc_arr[p1] + bc_arr[p2]) // 2 > tmp_sum:
                            tmp_sum1 = bc_arr[p1] // 2
                            tmp_sum2 = bc_arr[p2] // 2
                            tmp_sum = (bc_arr[p1] + bc_arr[p2]) // 2
                    else:
                        if bc_arr[p1] + bc_arr[p2] > tmp_sum:
                            tmp_sum1 = bc_arr[p1]
                            tmp_sum2 = bc_arr[p2]
                            tmp_sum = bc_arr[p1] + bc_arr[p2]
            sum1 += tmp_sum1
            sum2 += tmp_sum2
            if i != M:
                x1 += dx[n1[i]]; y1 += dy[n1[i]]
                x2 += dx[n2[i]]; y2 += dy[n2[i]]
        print('#{0} {1}'.format(tc + 1, sum1 + sum2))
