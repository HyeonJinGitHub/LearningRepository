if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        p, q = list(map(float, input().split()))
        s1 = (1-p)*q
        s2 = p*(1-q)*1*q
        if s1 < s2:
            print(f'#{i+1} YES')
        else:
            print(f'#{i+1} NO')