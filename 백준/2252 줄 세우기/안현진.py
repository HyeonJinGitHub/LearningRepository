import sys
from collections import deque

def topological_sort(adj_list):
    q = deque()
    for i in range(1, N + 1):
        if not in_degree[i]:
            q.append(i)
    new_arr = []
    for _ in range(N):
        if not q:
            return
        now = q.popleft()
        new_arr.append(now)
        for y in adj_list[now]:
            in_degree[y] -= 1
            if not in_degree[y]:
                q.append(y)
    for i in new_arr:
        print(i, end=' ')

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    in_degree = [0] * (N + 1)
    adj_list = [[] for _ in range(N + 1)]

    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        adj_list[u].append(v)
        in_degree[v] += 1
    topological_sort(adj_list)