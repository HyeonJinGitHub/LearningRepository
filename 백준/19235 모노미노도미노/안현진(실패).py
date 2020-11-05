import sys
from collections import deque

# def print_arr(arr):
#     for i in range(len(arr)):
#         for j in range(len(arr[i])):
#             print(arr[i][j], end=' ')
#         print()
#     print()
def move_right(group, right_d, group_num, right_q):
    q = deque()
    for i in range(4):
        for j in range(4):
            if board[i][j] == 0: continue
            if board[i][j] == -1:
                q.append((i, j))
                right_q.append(group_num)
    for i in range(4):
        ly = -1
        for j in range(10):
            if board[i][j] == 2 or board[i][j] == 0: continue
            if group[i][j] == group_num:
                ly = j
            if ly != -1 and group[i][j] != group_num and group[i][j] != 0:
                if right_d < j - ly:
                    continue
                else:
                    right_d = j - 1 - ly
    while q:
        x, y = q.pop()
        board[x][y + right_d] = board[x][y]
        group[x][y + right_d] = group[x][y]

def move_new_right(group, right_d, group_num):
    q = deque()
    for i in range(4):
        for j in range(4, 10):
            if board[i][j] == 2: continue
            if board[i][j] == -1 and group[i][j] == group_num:
                q.append((i, j))
    count = 0
    if not q:
        return
    for i in range(4):
        if count == len(q):
            break
        ly = -1
        for j in range(4, 10):
            if board[i][j] == 2: continue
            if group[i][j] == group_num:
                ly = j
                count += 1
                right_d = min(9-ly, right_d)
                #print(f' 여기 right d : {right_d}')
                if ly == 9:
                    return
            if ly != -1 and group[i][j] != group_num and group[i][j] != 0:
                if right_d < j - ly:
                    continue
                else:
                    # print(f'group 뭐야 {group_num}')
                    # print(f' j - ly : {j - ly}')
                    right_d = j - 1 - ly
    # print(f' group : {group_num}')
    # print(f'ly : {ly}')
    # print(f'right d: {right_d}')
    while q:
        x, y = q.pop()
        board[x][y + right_d] = board[x][y]
        group[x][y + right_d] = group[x][y]
        if right_d != 0:
            board[x][y] = 2
            group[x][y] = 0
    #print_arr(group)

def move_down(group, down_d, group_num, down_q):
    q = deque()
    for i in range(4):
        for j in range(4):
            if board[i][j] == 0: continue
            if board[i][j] == -1:
                q.append((i, j))
                down_q.append(group_num)
    for i in range(4):
        lx = -1
        for j in range(10):
            if board[j][i] == 1: continue
            if group[j][i] == group_num:
                lx = j
            if lx != -1 and group[j][i] != group_num and group[j][i] != 0:
                if down_d < j - lx:
                    continue
                else:
                    down_d = j - 1 - lx
    while q:
        x, y = q.pop()
        board[x + down_d][y] = board[x][y]
        group[x + down_d][y] = group[x][y]

def move_new_down(group, down_d, group_num):
    q = deque()
    for i in range(4, 10):
        for j in range(4):
            if board[i][j] == 1: continue
            if board[i][j] == -1 and group[i][j] == group_num:
                q.append((i, j))
    count = 0
    if not q:
        return
    for i in range(4):
        if count == len(q):
            break
        lx = -1
        for j in range(4, 10):
            if board[j][i] == 1: continue
            if group[j][i] == group_num:
                lx = j
                count += 1
                down_d = min(9-lx, down_d)
                if lx == 9:
                    return
            if lx != -1 and group[j][i] != group_num and group[j][i] != 0:
                if down_d < j - lx:
                    continue
                else:
                    down_d = j - 1 - lx
    # print(f' group : {group_num}')
    # print(f'lx : {lx}')
    # print(f'down d: {down_d}')
    while q:
        x, y = q.pop()
        board[x + down_d][y] = board[x][y]
        group[x + down_d][y] = group[x][y]
        if down_d != 0:
            board[x][y] = 1
            group[x][y] = 0
    #print_arr(group)

def solution(board, group, group_num, down_q, right_q):
    global res
    q = deque()
    lx = -1
    ly = -1
    for i in range(4):
        for j in range(4):
            if board[i][j] == 0 : continue
            if board[i][j] == -1:
                q.append((i, j))
                if lx < i:
                    lx = i
                if ly < j:
                    ly = j
                group[i][j] = group_num
    down_d = 9 - lx
    right_d = 9 - ly
    move_down(group, down_d, group_num, down_q)
    move_right(group, right_d, group_num, right_q)
    #print_arr(group)
    while True:
        flag = False
        flag_0 = False
        for i in range(9, 5, -1):
            if board[i].count(-1) == 4:
                flag = True
                res += 1
                #print_arr(group)
                #print(down_q)
                down_q.remove(group[i][0])
                down_q.remove(group[i][1])
                down_q.remove(group[i][2])
                down_q.remove(group[i][3])
                board[i][0], board[i][1], board[i][2], board[i][3], = 1, 1, 1, 1
                group[i][0], group[i][1], group[i][2], group[i][3], = 0, 0, 0, 0
                #print_arr(group)
        if flag:
            #for j in range(1, group_num + 1):
                #print(j)
            down_q = sorted(down_q)
            for idx in range(len(down_q)):
                grp_n = down_q[idx]
                move_new_down(group, 9, grp_n)
        clear_count = 0
        for i in range(4, 6):
            for j in range(4):
                if board[i][j] == -1:
                    clear_count += 1
                    break
        for i in range(clear_count):
            if group[9-i][0] != 0:
                down_q.remove(group[9-i][0])
            if group[9 - i][1] != 0:
                down_q.remove(group[9-i][1])
            if group[9 - i][2] != 0:
                down_q.remove(group[9-i][2])
            if group[9 - i][3] != 0:
                down_q.remove(group[9-i][3])
            board[9-i][0], board[9-i][1], board[9-i][2], board[9-i][3] = 1, 1, 1, 1
            group[9-i][0], group[9-i][1], group[9-i][2], group[9-i][3] = 0, 0, 0, 0
            flag = True
            flag_0 = True
        if flag_0:
            down_q = sorted(down_q)
            for idx in range(len(down_q)):
                grp_n = down_q[idx]
                move_new_down(group, 9, grp_n)
        if not flag:
            break
    #print(f'이거한번')
    #print_arr(board)
    while True:
        flag = False
        flag_0 = False
        for i in range(9, 5, -1):
            cnt = 0
            for j in range(4):
                if board[j][i] == -1:
                    cnt += 1
            if cnt == 4:
                flag = True
                res += 1
                right_q.remove(group[0][i])
                right_q.remove(group[1][i])
                right_q.remove(group[2][i])
                right_q.remove(group[3][i])
                board[0][i], board[1][i], board[2][i], board[3][i], = 2, 2, 2, 2
                group[0][i], group[1][i], group[2][i], group[3][i], = 0, 0, 0, 0
                #print('여기!!')
                #print_arr(group)
        if flag:
            right_q = sorted(right_q)
            for idx in range(len(right_q)):
                #print(j)
                grp_n = right_q[idx]
                move_new_right(group, 9, grp_n)
            #break
        clear_count = 0
        for i in range(5, 3, -1):
            for j in range(4):
                if board[j][i] == -1:
                    clear_count += 1
                    break
        for i in range(clear_count):
            if group[0][9-i] != 0:
                right_q.remove(group[0][9-i])
            if group[1][9 - i] != 0:
                right_q.remove(group[1][9-i])
            if group[2][9 - i] != 0:
                right_q.remove(group[2][9-i])
            if group[3][9 - i] != 0:
                right_q.remove(group[3][9-i])
            board[0][9-i], board[1][9-i], board[2][9-i], board[3][9-i] = 2, 2, 2, 2
            group[0][9-i], group[1][9-i], group[2][9-i], group[3][9-i] = 0, 0, 0, 0
            flag = True
            flag_0 = True
        if flag_0:
            right_q = sorted(right_q)
            for idx in range(len(right_q)):
                grp_n = right_q[idx]
                move_new_right(group, 9, grp_n)
        if not flag:
            break
    #print_arr(group)

if __name__ == '__main__':
    board = [[0] * 10 for _ in range(10)]
    res = 0
    for i in range(4, 10):
        for j in range(4):
            board[i][j] = 1
    for i in range(4):
        for j in range(4, 10):
            board[i][j] = 2
    N = int(input())
    group_num = 1
    group = [[0] * 10 for _ in range(10)]
    down_q = deque()
    right_q = deque()
    for _ in range(N):
        t, x, y = map(int, sys.stdin.readline().split())
        board[x][y] = -1
        if t == 2:
            board[x][y+1] = -1
        elif t == 3:
            board[x + 1][y] = -1
        #print_arr(board)
        solution(board, group, group_num, down_q, right_q)
        board[x][y] = 0
        group[x][y] = 0
        if t == 2:
            board[x][y + 1] = 0
            group[x][y + 1] = 0
        elif t == 3:
            board[x + 1][y] = 0
            group[x + 1][y] = 0

        group_num += 1
        #print_arr(group)
    tile = 0
    print(res)
    for i in range(4, 10):
        for j in range(4):
            if board[i][j] == -1:
                tile += 1
    for i in range(4):
        for j in range(4, 10):
            if board[i][j] == -1:
                tile += 1
    print(tile)


# print(10000)
# for i in range(5000):
#     print(2, 2, 2)
#     print(3, 1, 1)
#
# print(10000)
# for i in range(10000):
#     print(1, 1, 1)
#
#



