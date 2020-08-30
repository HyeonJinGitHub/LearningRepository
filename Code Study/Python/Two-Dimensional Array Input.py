N = int(input())
# NxN 2차원 배열 값 입력받아서 arr1에 저장
arr1 = [[int(x) for x in input().split()] for y in range(N)]
# NxN 2차원 배열 0으로 초기화
arr2 = [[0 for col in range(N)] for row in range(N)]