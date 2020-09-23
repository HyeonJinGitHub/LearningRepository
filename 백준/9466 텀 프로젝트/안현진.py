import sys

def dfs(x):
    global result
    cycle.append(x)
    check[x] = True
    number = students[x]

    if check[number]:
        if number in cycle:
            result += cycle[cycle.index(number):]
        return
    else:
        dfs(number)

if __name__ == '__main__':
    sys.setrecursionlimit(100000)
    for _ in range(int(input())):
        N = int(input())
        students = [0] + list(map(int, sys.stdin.readline().split()))
        check = [True] + [False] * N
        result = []

        for i in range(1, N + 1):
            if not check[i]:
                cycle = []
                dfs(i)
        print(N - len(result))