if __name__== '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        res = 0
        for j in range(N):
            tmp = list(map(str, input().split()))
            res += float(tmp[0])*int(tmp[1])
        print(f'#{i + 1} {res}')