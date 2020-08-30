T = int(input())
for i in range(T):
    N = int(input())
    item = list(map(int, input().split()))[::-1]
    answer = 0
    now_max = item[0]
    for j in range(1,N):
        if now_max > item[j]:
            answer += now_max - item[j]
        else:
            now_max = item[j]
    print(f'#{i+1} {answer}')