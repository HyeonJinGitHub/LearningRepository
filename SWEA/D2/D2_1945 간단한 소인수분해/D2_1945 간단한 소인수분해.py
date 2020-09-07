if __name__ == '__main__':
    T = int(input())
    lis = [2, 3, 5, 7, 11]
    for i in range(T):
        res = '#' + str(i + 1)
        N = int(input())
        count = 0
        j = 0
        while j < len(lis):
            if N % lis[j] == 0:
                count += 1
                N /= lis[j]
            else:
                res += ' ' + str(count)
                count = 0
                j += 1
        print(res)