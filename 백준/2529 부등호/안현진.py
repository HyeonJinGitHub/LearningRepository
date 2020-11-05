import sys

def possible(i, j, k):
    if k == '>':
        return i > j
    if k == '<':
        return i < j
    return True

def solution(cnt, s):
    global mx, mn
    if cnt == N + 1:
        if not len(mn):
            mn = s
        else:
            mx = s
        return
    for i in range(10):
        if not check[i]:
            if cnt == 0 or possible(s[-1], str(i), op[cnt - 1]):
                check[i] = True
                solution(cnt + 1, s + str(i))
                check[i] = False

if __name__ == '__main__':
    N = int(input())
    op = list(map(str, sys.stdin.readline().split()))
    check = [False] * 10
    mx, mn = '', ''
    solution(0, '')
    print(mx)
    print(mn)