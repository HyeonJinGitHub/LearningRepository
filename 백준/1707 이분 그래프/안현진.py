import sys
from collections import deque

def bfs(x):
    q = deque([x])
    check[x] = True

    while q:
        x = q.popleft()
        for y in a[x]:
            if not check[y]:
                check[y] = True
                if not color[x]:
                    color[y] = True
                else:
                    color[y] = False
                q.append(y)
            else:
                if color[y] == color[x]:
                    return False
    return True

if __name__ == '__main__':
    for _ in range(int(input())):
        V, E = map(int, sys.stdin.readline().split())
        a = [[] for _ in range(V)]
        check = [False] * V
        color = [False] * V
        for _ in range(E):
            u, v = map(int, sys.stdin.readline().split())
            a[u - 1].append(v - 1)
            a[v - 1].append(u - 1)
        ch = False
        for i in range(V):
            if not check[i]:
                ch = bfs(i)
                if not ch:
                    print('NO')
                    break
        if ch:
            print('YES')
