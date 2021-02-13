import sys
from collections import deque

def reverse_up_down():
    global board
    board = board[::-1]

def reverse_left_right():
    global board
    for i in range(N):
        board[i] = board[i][::-1]

def rotate_right():
    global N, M, board
    N, M = M, N
    tmp_board = [list(x)[::-1] for x in zip(*board)]
    board = tmp_board

def rotate_left():
    global N, M, board
    N, M = M, N
    tmp_board = [list(x) for x in list(zip(*board))[::-1]]
    board = tmp_board

def mix_right():
    global board
    halfN = N // 2
    halfM = M // 2
    tmp_board = [[0] * M for _ in range(N)]

    for i in range(halfN):
        for j in range(halfM):
            tmp_board[i][j + halfM] = board[i][j]
    for i in range(halfN):
        for j in range(halfM, M):
            tmp_board[i + halfN][j] = board[i][j]
    for i in range(halfN, N):
        for j in range(halfM, M):
            tmp_board[i][j - halfM] = board[i][j]
    for i in range(halfN, N):
        for j in range(halfM):
            tmp_board[i - halfN][j] = board[i][j]
    for i in range(N):
        for j in range(M):
            board[i][j] = tmp_board[i][j]

def mix_left():
    global board
    halfN = N // 2
    halfM = M // 2
    tmp_board = [[0] * M for _ in range(N)]

    for i in range(halfN):
        for j in range(halfM):
            tmp_board[i + halfN][j] = board[i][j]
    for i in range(halfN, N):
        for j in range(halfM):
            tmp_board[i][j + halfM] = board[i][j]
    for i in range(halfN, N):
        for j in range(halfM, M):
            tmp_board[i - halfN][j] = board[i][j]
    for i in range(halfN):
        for j in range(halfM, M):
            tmp_board[i][j - halfM] = board[i][j]
    for i in range(N):
        for j in range(M):
            board[i][j] = tmp_board[i][j]

def find_state():
    state = [[1, 0], [2, 0], [3, 0], [4, 0]]
    for y in cmd:
        if y == 1:
            state[0], state[3] = state[3], state[0]
            state[1], state[2] = state[2], state[1]
            for i in range(4):
                state[i][1] = (state[i][1] + 2) % 4
        elif y == 2:
            state[0], state[1] = state[1], state[0]
            state[2], state[3] = state[3], state[2]
            for i in range(4):
                if state[i][1] == 1: state[i][1] = 0
                elif state[i][1] == 0: state[i][1] = 1
                elif state[i][1] == 2: state[i][1] = 3
                elif state[i][1] == 3: state[i][1] = 2
        elif y == 3:
            q = deque(state)
            q.rotate(1)
            state = list(q)
            for i in range(4):
                if state[i][1] == 1:
                    state[i][1] = 3
                elif state[i][1] == 0:
                    state[i][1] = 1
                elif state[i][1] == 2:
                    state[i][1] = 0
                elif state[i][1] == 3:
                    state[i][1] = 2
        elif y == 4:
            q = deque(state)
            q.rotate(-1)
            state = list(q)
            for i in range(4):
                if state[i][1] == 1:
                    state[i][1] = 0
                elif state[i][1] == 0:
                    state[i][1] = 2
                elif state[i][1] == 2:
                    state[i][1] = 3
                elif state[i][1] == 3:
                    state[i][1] = 1
        elif y == 5:
            q = deque(state)
            q.rotate(1)
            state = list(q)
        elif y == 6:
            q = deque(state)
            q.rotate(-1)
            state = list(q)
    return state

def move_board(r, x, r1, x1):
    nxt = (r + 1) % 4
    if not nxt: nxt = 4
    if r == 1:
        if x == 0:
            if r1 != nxt:
                reverse_left_right()
                rotate_left()
        if x == 1:
            if r1 == nxt:
                rotate_right()
                mix_left()
            else:
                reverse_left_right()
                mix_left()
        elif x == 2:
            if r1 == nxt:
                rotate_left()
                mix_right()
            else:
                reverse_up_down()
                mix_right()
        elif x == 3:
            if r1 == nxt:
                reverse_up_down()
                reverse_left_right()
                mix_left()
                mix_left()
            else:
                rotate_right()
                reverse_up_down()
                mix_left()
                mix_left()
    elif r == 2:
        if x == 0:
            if r1 == nxt:
                mix_left()
            else:
                reverse_up_down()
                rotate_right()
                mix_right()
        elif x == 1:
            if r1 == nxt:
                rotate_right()
                mix_left()
                mix_left()
            else:
                reverse_left_right()
        elif x == 2:
            if r1 == nxt:
                rotate_left()
            else:
                reverse_up_down()
                mix_right()
                mix_right()
        elif x == 3:
            if r1 == nxt:
                reverse_up_down()
                reverse_left_right()
                mix_right()
            else:
                rotate_right()
                reverse_up_down()
                mix_left()
    elif r == 3:
        if x == 0:
            if r1 == nxt:
                mix_left()
                mix_left()
            else:
                reverse_up_down()
                rotate_right()
                mix_right()
                mix_right()
        elif x == 1:
            if r1 == nxt:
                rotate_right()
                mix_right()
            else:
                reverse_left_right()
                mix_right()
        elif x == 2:
            if r1 == nxt:
                rotate_left()
                mix_left()
            else:
                reverse_up_down()
                mix_left()
        elif x == 3:
            if r1 == nxt:
                reverse_up_down()
                reverse_left_right()
            else:
                reverse_up_down()
                rotate_left()
    elif r == 4:
        if x == 0:
            if r1 == nxt:
                mix_right()
            else:
                reverse_left_right()
                rotate_left()
                mix_left()
        elif x == 1:
            if r1 == nxt:
                rotate_right()
            else:
                reverse_left_right()
                mix_right()
                mix_right()
        elif x == 2:
            if r1 == nxt:
                rotate_left()
                mix_left()
                mix_left()
            else:
                reverse_up_down()
        elif x == 3:
            if r1 == nxt:
                reverse_left_right()
                reverse_up_down()
                mix_left()
            else:
                reverse_left_right()
                rotate_right()
                mix_right()

if __name__ == '__main__':
    N, M, R = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    cmd = list(map(int, sys.stdin.readline().split()))
    final_state = find_state()
    r, x = final_state[0]
    r1, x1 = final_state[1]
    move_board(r, x, r1, x1)
    for i in range(N):
        print(' '.join(map(str, board[i])))
