import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**8)
memo = {}
def solution(total, day, late, abs):
    if late >= 2:
        return 0
    if abs >= 3:
        return 0
    if day == total:
        return 1

    if (day, late, abs) in memo:
        return memo[(day, late, abs)]

    memo[(day, late, abs)] = solution(total, day + 1, late, 0) + solution(total, day + 1, late + 1, 0) + solution(total, day + 1, late, abs + 1)
    return memo[(day, late, abs)]
if __name__ == '__main__':
    N = int(input())
    print(solution(N, 0, 0, 0) % 1000000)