import sys

def find(x):
    if x == parent[x]:
        return x
    parent[x] = find(parent[x])
    return parent[x]

def union(x, y):
    x, y = find(x), find(y)
    if x != y:
        parent[x] = y

if __name__ == '__main__':
    V, E = map(int, sys.stdin.readline().split())
    parent = [i for i in range(V + 1)]
    arr = [list(map(int, sys.stdin.readline().split())) for _ in range(E)]
    arr = sorted(arr, key=lambda x: x[2])
    ans = 0
    for i in range(len(arr)):
        x, y, w = arr[i]
        if find(x) == find(y):
            continue
        union(x, y)
        ans += w
    print(ans)
