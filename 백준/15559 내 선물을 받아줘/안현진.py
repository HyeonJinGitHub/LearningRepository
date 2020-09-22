import sys

def dfs(x):
    global result
    cycle.append(x)
    check[x] = True
    number = b[x]
    if check[number]:
        if number in cycle:
            result += 1
        return
    else:
        dfs(number)

if __name__ == '__main__':
    sys.setrecursionlimit(500001)
    N, M = map(int, input().split())
    a = [list(map(str, sys.stdin.readline().strip())) for _ in range(N)]
    b = [0] * (N*M + 1)
    check = [True] + [False] * (N*M)
    cnt = 1
    tmp = {}
    result = 0
    for i in range(len(a)):
        for j in range(len(a[i])):
            tmp[cnt] = a[i][j]
            cnt += 1
    for i in range(1, N*M + 1):
        if tmp[i] == 'N':
            b[i] = (i - M)
        elif tmp[i] == 'S':
            b[i] = (i + M)
        elif tmp[i] == 'W':
            b[i]= (i - 1)
        elif tmp[i] == 'E':
            b[i]= (i + 1)
    for i in range(1, N*M + 1):
        if not check[i]:
            cycle = []
            dfs(i)
    print(result)

# 밑에는 메모리도 위 코드보다 적게 잡고 시간도 훨씬 빠른 다른 사람의 코드이다.
# 정말 효율적으로 잘 짠 코드인 것 같다. 대단한 것 같다.
# 저런 생각을 할 수 있을때까지 연습해야겠다.

# n, m = map(int, input().split())
# MAP = [list(input()) for _ in range(n)]
# check = [[0]*m for _ in range(n)]
# dic = {'N':(-1,0), 'S':(1,0), 'E':(0,1), 'W':(0,-1)}
#
# def dfs(y,x,cnt):
#     global n, m
#     if check[y][x]:
#         return check[y][x]
#
#     check[y][x] = cnt
#     ny = y + dic[MAP[y][x]][0]
#     nx = x + dic[MAP[y][x]][1]
#     check[y][x] = dfs(ny, nx, cnt)
#     return check[y][x]
#
# result = 0
# for i in range(n):
#     for j in range(m):
#         if not check[i][j]:
#             a = dfs(i,j,result+1)
#             result = max(a, result)
#
# print(result)