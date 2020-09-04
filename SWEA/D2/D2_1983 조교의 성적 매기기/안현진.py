import operator

T = int(input())
for i in range(T):
    N, K = list(map(int, input().split()))
    n = N//10
    list1 = []
    score = ['D0', 'C-', 'C0', 'C+', 'B-', 'B0', 'B+', 'A-', 'A0', 'A+']
    for j in range(N):
        arr = list(map(int, input().split()))
        total = arr[0]*0.35 + arr[1]*0.45 + arr[2]*0.2
        list1.append(total)
    list3 = dict(zip(range(1, len(list1)+1), list1))
    sorted_dict = dict(sorted(list3.items(), key=operator.itemgetter(1)))
    res = {}
    for j, tmp in enumerate(list(sorted_dict.keys())):
        res[tmp] = score[j//n]
    print(f'#{i+1} {res[K]}')