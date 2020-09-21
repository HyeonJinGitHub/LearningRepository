import sys
import heapq

def bfs():
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    arr = []
    q = []
    for i in range(len(a)):
        for j in range(len(a[i])):
            if a[i][j] != 0:
                heapq.heappush(q, (a[i][j], i, j))
    flag = False
    for _ in range(s):
        if a[tx - 1][ty - 1] != 0:
            flag = True
            print(a[tx - 1][ty - 1])
            break
        while q:
            v, x, y = heapq.heappop(q)
            for k in range(4):
                nx, ny = x + dx[k], y + dy[k]
                if 0 <= nx < n and 0 <= ny < n:
                    if a[nx][ny] == 0:
                        a[nx][ny] = a[x][y]
                        arr.append((a[nx][ny], nx, ny))
                        if nx == tx - 1 and ny == ty - 1:
                            print(a[tx - 1][ty - 1])
                            return
        while arr:
            heapq.heappush(q, arr.pop())
    if not flag:
        print(a[tx-1][ty-1])

if __name__ == '__main__':
    n, k = map(int, sys.stdin.readline().split())
    a = [[] for _ in range(n)]
    for i in range(n):
        a[i] = list(map(int, sys.stdin.readline().split()))
    s, tx, ty = map(int, sys.stdin.readline().split())
    bfs()

# 밑에 코드처럼 PriorityQueue 를 사용하면 시간초과로 통과하지 못한다.
# 정확한 이유는 내부 동작 흐름을 봐야겠지만, 여러 검색을 한 결과 PriorityQueue가 heapq보다 느리다고 한다.
# 우선순위큐로 문제를 풀 경우 heapq 를 사용하는 것이 나은것 같다.

#
# import sys
# import queue
#
# def bfs():
#     dx = [0, 0, 1, -1]
#     dy = [1, -1, 0, 0]
#     arr = []
#     q = queue.PriorityQueue()
#     for i in range(len(a)):
#         for j in range(len(a[i])):
#             if a[i][j] != 0:
#                 q.put((a[i][j], i, j))
#
#
#     flag = False
#     for tt in range(s):
#         if a[tx - 1][ty - 1] != 0:
#             flag = True
#             print(a[tx - 1][ty - 1])
#             break
#         while q:
#             v, x, y = q.get()
#             for k in range(4):
#                 nx, ny = x + dx[k], y + dy[k]
#                 if 0 <= nx < n and 0 <= ny < n:
#                     if a[nx][ny] == 0:
#                         a[nx][ny] = a[x][y]
#                         arr.append((a[nx][ny], nx, ny))
#                         if nx == tx - 1 and ny == ty -1:
#                             print(a[tx - 1][ty - 1])
#                             return
#             if q.qsize() == 0:
#                 break
#         while arr:
#             q.put(arr.pop())
#     if not flag:
#         print(a[tx - 1][ty - 1])
#
# if __name__ == '__main__':
#     n, k = map(int, sys.stdin.readline().split())
#     a = [[] for _ in range(n)]
#     for i in range(n):
#         a[i] = list(map(int, sys.stdin.readline().strip().split()))
#     s, tx, ty = map(int, sys.stdin.readline().strip().split())
#     bfs()