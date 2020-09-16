# import sys
# if __name__ == '__main__':
#     arr = [0] * 12
#     arr[0] = 1
#     arr[1] = 1
#     arr[2] = 2
#     for i in range(3, 11):
#         arr[i] = arr[i-1] + arr[i-2] + arr[i-3]
#     for _ in range(int(sys.stdin.readline())):
#         n = int(sys.stdin.readline())
#         sys.stdout.writelines(str(arr[n]) + '\n')

def solution(s, goal):
    if s > goal:
        return 0
    if s == goal:
        return 1
    now = 0
    for i in range(1, 4):
        now += solution(s + i, goal)
    return now
if __name__ == '__main__':
    for _ in range(int(input())):
        N = int(input())
        print(solution(0, N))