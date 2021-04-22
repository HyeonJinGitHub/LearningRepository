from itertools import combinations

def solution(stair_list, stair):
    wait_d = 0
    delete_list = []
    time = 0
    while stair_list or delete_list or wait_d:
        while wait_d:
            if len(delete_list) >= 3: break
            delete_list.append(stair)
            wait_d -= 1

        for i in range(len(delete_list) - 1, -1, -1):
            delete_list[i] -= 1
            if delete_list[i] <= 0:
                delete_list.pop(i)

        for i in range(len(stair_list) - 1, -1, -1):
            stair_list[i] -= 1
            if stair_list[i] <= 0:
                stair_list.pop(i)
                wait_d += 1
        time += 1
    return time

def comb(person):
    global ans
    size = len(person)

    for i in range(size + 1):
        for c in combinations(person, i):
            a = list(c)
            b = list(set(person) - set(c))
            first, second = [], []
            for p1 in a:
                first.append(p1[0])
            for p2 in b:
                second.append(p2[1])
            cnt = max(solution(first, stair[0][2]), solution(second, stair[1][2]))
            ans = min(ans, cnt)

if __name__ == '__main__':
    for tc in range(int(input())):
        N = int(input())
        board = [list(map(int, input().split())) for _ in range(N)]
        person = []
        stair = []

        for i in range(N):
            for j in range(N):
                if board[i][j] == 1:
                    person.append([i, j])
                elif board[i][j] > 1:
                    stair.append((i, j, board[i][j]))
        ans = 10**9
        for i in range(len(person)):
            distance1 = abs(person[i][0] - stair[0][0]) + abs(person[i][1] - stair[0][1])
            distance2 = abs(person[i][0] - stair[1][0]) + abs(person[i][1] - stair[1][1])
            person[i] = (distance1, distance2, i)
        comb(person)
        print('#{0} {1}'.format(tc + 1, ans + 1))

