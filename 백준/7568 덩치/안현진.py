import sys
if __name__ == '__main__':
    N = int(input())
    student = []
    for _ in range(N):
        w, h = map(int, sys.stdin.readline().split())
        student.append((w, h))
    for i in student:
        rank = 1
        for j in student:
            if i == j:continue
            if i[0] < j[0] and i[1] < j[1]:
                rank += 1
        print(rank, end=' ')