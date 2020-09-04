def SpiralArray(x, y):
    sa_list = [[0 for x in range(x)] for y in range(y)]
    count = 1
    flag = 0
    i, j = 0, 0

    while True:
        if flag == 0:
            sa_list[i][j] = count
            j += 1
            count += 1
            if j == y or sa_list[i][j] != 0:
                j -= 1
                i += 1
                flag = 1
        elif flag == 1:
            sa_list[i][j] = count
            i += 1
            count += 1
            if i == x or sa_list[i][j] != 0:
                i -= 1
                j -= 1
                flag = 2
        elif flag == 2:
            sa_list[i][j] = count
            j -= 1
            count += 1
            if j == -1 or sa_list[i][j] != 0:
                i -= 1
                j += 1
                flag = 3
        elif flag == 3:
            sa_list[i][j] = count
            i -= 1
            count += 1
            if i == 0 or sa_list[i][j] != 0:
                i += 1
                j += 1
                flag = 0

        if count == x*y+1:
            break
    for arr in sa_list:
        for res in arr:
            print(f'{res}', end=' ')
        print()

if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        print(f'#{i+1}')
        SpiralArray(N, N)