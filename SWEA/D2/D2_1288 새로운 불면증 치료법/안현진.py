if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        arr = [0]*10
        count = 1
        while True:
            tmp = str(count*N)
            check = False
            for j in range(len(tmp)):
                arr[int(tmp[j])] += 1
            for j in range(len(arr)):
                if arr[j] == 0:
                    check = False
                    break
                else:
                    check = True
            if check:
                print(f'#{i+1} {count*N}')
                break
            count += 1
