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
    N, M = map(int, sys.stdin.readline().split())
    real_person = list(map(int, sys.stdin.readline().split()))
    parent = [i for i in range(N + 1)]
    partys = []
    if real_person[0] == 0:
        print(M)
        exit(0)
    for p in range(M):
        party = list(map(int, sys.stdin.readline().split()))[1:]
        party.sort()
        for i in range(len(party) - 1):
            union(party[i], party[-1])
        partys.append(party[0])
    person = real_person[1:]
    ans = 0
    for party in partys:
        flag = False
        for p in person:
            if find(party) == find(p):
                flag = True
                break
        if not flag:
            ans += 1
    print(ans)