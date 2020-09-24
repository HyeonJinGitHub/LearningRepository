if __name__ == '__main__':
    N = int(input())
    cnt = 0
    res = []

    for i in range(1, N + 1):
        arr = [N, i]
        while True:
            tmp = arr[len(arr) - 2] - arr[len(arr) - 1]
            if tmp < 0:
                break
            arr.append(tmp)
        if cnt < len(arr):
            cnt = len(arr)
            res.clear()
            for j in range(len(arr)):
                res.append(arr[j])
    print(cnt)
    while res:
        print(res.pop(0), end=' ')