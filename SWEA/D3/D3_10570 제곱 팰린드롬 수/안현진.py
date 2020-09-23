import math

if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        count = 0
        A, B = list(map(int, input().split()))
        for j in range(A, B+1):
            s1 = str(j)
            if s1 == s1[::-1]:
                if j % math.sqrt(j) == 0.0:
                    s2 = str(int(math.sqrt(j)))
                    if s2 == s2[::-1]:
                        count += 1
        print(f'#{i+1} {count}')