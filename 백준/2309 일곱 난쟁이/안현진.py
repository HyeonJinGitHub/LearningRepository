import sys

if __name__ == '__main__':
    arr = []
    arr_sum = 0
    flag = False
    for _ in range(9):
        tmp = int(sys.stdin.readline())
        arr.append(tmp)
        arr_sum += tmp
    for i in range(len(arr)):
        for j in range(i + 1, len(arr)):
            if arr_sum - (arr[i] + arr[j]) == 100:
                arr.pop(i)
                arr.pop(j - 1)
                flag = True
                break
        if flag:
            break
    arr.sort()
    for i in range(len(arr)):
        print(arr[i])