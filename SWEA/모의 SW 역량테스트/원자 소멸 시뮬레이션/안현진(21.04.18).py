from collections import deque
from collections import defaultdict

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

def solution(atom):
    global ans
    q = deque(atom)
    while q:
        size = len(q)
        if size == 1: break
        tmp = defaultdict(lambda :[])
        for _ in range(size):
            if not q: break
            x, y, d, e = q.popleft()
            board[x][y] = 0
            nx, ny = x + dx[d], y + dy[d]
            if nx < 0 or nx > 2000 or ny < 0 or ny > 2000: continue
            if board[nx][ny]:
                flag = False
                for tmp_next in q:
                    nnx, nny, nd, ne = tmp_next
                    if nx == nnx and ny == nny:
                        if (d == 0 and nd == 1) or (d == 1 and nd == 0) or (d == 2 and nd == 3) or (d == 3 and nd == 2):
                            ans += (e + ne)
                            flag = True
                            board[nnx][nny] = 0
                            q.remove(tmp_next)
                        break
                if flag: continue
            tmp[(nx, ny)].append((x, y, d, e))
        for k in tmp.keys():
            nxt_x, nxt_y = k[0], k[1]
            if len(tmp[k]) == 1:
                prev_x, prev_y, d, e = tmp[k][0]
                board[nxt_x][nxt_y] = e
                q.append((nxt_x, nxt_y, d, e))
                continue
            elif len(tmp[k]) > 1:
                for delete_atom in tmp[k]:
                    ans += delete_atom[3]

if __name__ == '__main__':
    board = [[0] * 2001 for _ in range(2001)]
    for tc in range(int(input())):
        ans = 0
        N = int(input())
        init_atom = []
        for _ in range(N):
            x, y, d, e = map(int, input().split())
            board[-y + 1000][x + 1000] = e
            init_atom.append((-y + 1000, x + 1000, d, e))
        solution(init_atom)
        print('#{0} {1}'.format(tc + 1, ans))

