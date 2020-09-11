if __name__ == '__main__':
    for i in range(int(input())):
        N = int(input())
        arr = list(map(int, input().split()))
        res_arr = []
        if len(arr) == 1:
            res_arr.append(arr[0])
        for j in range(len(arr) - 1):
            for k in range(j + 1, len(arr)):
                tmp = arr[j] * arr[k]
                str_tmp = str(tmp)
                idx_start = 0
                if len(str_tmp) == 1:
                    res_arr.append(tmp)
                for l in range(1, len(str_tmp)):
                    if int(str_tmp[idx_start]) > int(str_tmp[l]):
                        break
                    idx_start = l
                    if l == len(str_tmp) - 1:
                        res_arr.append(tmp)
        res_arr.sort()
        if len(res_arr) == 0:
            print(f'#{i + 1} -1')
        else:
            print(f'#{i + 1} {res_arr[len(res_arr) - 1]}')

