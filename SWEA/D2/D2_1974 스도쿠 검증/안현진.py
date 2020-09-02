def solution(arr):
    for i in range(9):
        # 가로 검사
        if len(set(arr[i])) != 9:
            return 0
        # 세로 검사
        tmp = set()
        for j in range(9):
            tmp.add(arr[j][i])
        if len(tmp) != 9:
            return 0
    # 9개 구역 검사
    line_array = [set(), set(), set()]
    index = 0
    for r in range(0, 81):
        x = r // 9
        y = r % 9

        if r != 0 and r % 27 == 0:
            if len(line_array[0]) != 9 and len(line_array[1]) != 9 and len(line_array[2]) != 9:
                return 0
        if r != 0 and r % 3 == 0:
            if index == 2:
                index = -1
            index += 1
        line_array[index].add(arr[x][y])
    return 1
if __name__ == "__main__":
    T = int(input())
    for i in range(T):
        arr = [[int(x) for x in input().split()] for y in range(9)]
        print(f'#{i+1} {solution(arr)}')