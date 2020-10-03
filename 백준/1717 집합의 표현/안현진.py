import sys

def Find(x):
    if x == parents[x]:
        return x
    else:
        y = Find(parents[x])
        parents[x] = y
        return y
def Union(x, y):
    x = Find(x)
    y = Find(y)
    if x != y:
        parents[y] = x
if __name__ == '__main__':
    n, m =map(int, sys.stdin.readline().split())
    parents = [i for i in range(n + 1)]
    for _ in range(m):
        command, a, b = map(int, sys.stdin.readline().split())
        if command == 0:
            Union(a, b)
        elif command == 1:
            a_parents = Find(a)
            b_parents = Find(b)
            if a_parents == b_parents:
                sys.stdout.writelines('YES' + '\n')
            else:
                sys.stdout.writelines('NO' + '\n')