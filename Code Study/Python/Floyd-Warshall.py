import sys
INF = sys.maxsize

def floyd_warhsall():
    for k in range(1, n + 1):
        for i in range(1, n + 1):
            if k == i: continue
            for j in range(1, n + 1):
                if k == j: continue
                if graph[i][j] > graph[i][k] + graph[k][j]:
                    graph[i][j] = graph[i][k] + graph[k][j]
                    v[i][j] = v[i][k]

if __name__ == '__main__':
    n, m = map(int, sys.stdin.readline().split())
    graph = [[INF] * (n + 1) for _ in range(n + 1)]
    v = [[INF] * (n + 1) for _ in range(n + 1)]

    for _ in range(m):
        a, b, c = map(int, sys.stdin.readline().split())
        graph[a][b] = c
        graph[b][a] = c
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if i == j: continue
            if graph[i][j] != INF:
                v[i][j] = j
    floyd_warhsall()