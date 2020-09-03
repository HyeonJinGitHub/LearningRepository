def rotate(m, d):
    """2차원 배열을 90도 단위로 회전해 반환한다.
       이때 원 배열은 유지되며, 새로운 배열이 탄생한다. 이는 회전이 360도 단위일 때도 해당한다.
       2차원 배열은 행과 열의 수가 같은 정방형 배열이어야 한다.

       :input:
       m: 회전하고자 하는 2차원 배열. 입력이 정방형 행렬이라고 가정한다.
       d: 90도씩의 회전 단위. -1: -90도, 1: 90도, 2: 180도, ...
    """
    N = len(m)
    ret = [[0] * N for _ in range(N)]

    if d % 4 == 1:
        for r in range(N):
            for c in range(N):
                ret[c][N-1-r] = m[r][c]
    elif d % 4 == 2:
        for r in range(N):
            for c in range(N):
                ret[N-1-r][N-1-c] = m[r][c]
    elif d % 4 == 3:
        for r in range(N):
            for c in range(N):
                ret[N-1-c][r] = m[r][c]
    else:
        for r in range(N):
            for c in range(N):
                ret[r][c] = m[r][c]

    return ret

if __name__ == "__main__":
    T = int(input())
    for i in range(T):
        N = int(input())
        arr = [[int(x) for x in input().split()] for y in range(N)]
        list1 = []
        for j in range(1, 4):
            res = rotate(arr, j)
            for k in range(len(res)):
                list1.append(res[k])
        print(f'#{i+1}')
        tmp = 0
        t = 0
        while tmp < len(list1):
            for k in range(len(list1[t])):
                print(f'{list1[t][k]}', end='')
            print(end=' ')
            t += N
            tmp += 1
            if t >= len(list1):
                t -= (len(list1)-1)
                print()