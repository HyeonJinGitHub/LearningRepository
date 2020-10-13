import sys
from collections import deque
from collections import defaultdict

def bfs(s_x, s_y, d_x, d_y):
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    if s_x == d_x and s_y == d_y:
        return 0
    q = deque([(s_x, s_y)])
    dist = [[0] * N for _ in range(N)]
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < N and 0 <= ny < N:
                if board[nx][ny] == 0 and dist[nx][ny] == 0:
                    dist[nx][ny] = dist[x][y] + 1
                    q.append((nx, ny))
                    if nx == d_x and ny == d_y:
                        return dist[d_x][d_y]
    return -1
def solution(distance, start_distance, start_dict, dest_dict):
    global K, M
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]
    while True:
        d, (dd_x, dd_y) = start_distance.pop(0)
        K -= d
        if K < 0:
            print(-1)
            return
        person_number = start_dict[(dd_x, dd_y)][0]
        del start_dict[(dd_x, dd_y)]
        K -= distance[person_number - 1]
        if K < 0:
            print(-1)
            return
        K += distance[person_number - 1] * 2
        M -= 1
        if M == 0:
            print(K)
            return
        ss_x, ss_y = 0, 0
        for x, y in dest_dict:
            if person_number in dest_dict[(x, y)]:
                ss_x, ss_y = x, y
                dest_dict[(x, y)].remove(person_number)
                break
        dd_x, dd_y = 0, 0

        start_distance = []
        dist = [[0] * N for _ in range(N)]
        check = [[False] * N for _ in range(N)]
        q = deque([(ss_x,ss_y)])
        check[ss_x][ss_y] = True
        while q:
            x, y = q.popleft()
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < N and 0 <= ny < N:
                    if board[nx][ny] == 0 and dist[nx][ny] == 0 and not check[nx][ny]:
                        check[nx][ny] = True
                        dist[nx][ny] = dist[x][y] + 1
                        q.append((nx, ny))
        for x, y in start_dict:
            dd_x, dd_y = x, y
            start_distance.append((dist[dd_x][dd_y], (dd_x, dd_y)))
        start_distance.sort()

if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    taxi_x, taxi_y = map(int, sys.stdin.readline().split())
    distance = []
    start_distance = []
    start_dict = defaultdict(lambda :[])
    dest_dict = defaultdict(lambda :[])
    flag = False
    for i in range(1, M + 1):
        s_x, s_y, d_x, d_y = map(int, sys.stdin.readline().split())
        first_d = bfs(taxi_x - 1, taxi_y - 1, s_x - 1, s_y - 1)
        if first_d == -1:
            flag = True
            continue
        start_distance.append((first_d, (s_x - 1, s_y - 1)))
        dest_dict[(d_x - 1, d_y - 1)].append(i)
        start_dict[(s_x - 1, s_y - 1)].append(i)
        a = bfs(s_x - 1, s_y - 1, d_x - 1, d_y - 1)
        if a == -1:
            flag = True
            continue
        distance.append(a)
    start_distance.sort()

    if not flag:
        solution(distance, start_distance, start_dict,dest_dict)
    else:
        print(-1)