if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        count = 0
        print(f'#{i+1}')
        res = ''
        for j in range(N):
            tmp = input().split()
            ch = tmp[0]
            n = int(tmp[1])
            for k in range(n):
                count += 1
                res += ch
                if count % 10 == 0:
                    res += '\n'
        print(res)