import sys

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

if __name__ == '__main__':
    A, B = map(int, sys.stdin.readline().split())
    N, M = map(int, sys.stdin.readline().split())
    board = [[-1] * A for _ in range(B)]
    robots = dict()
    for i in range(N):
        a, b, c = map(str, sys.stdin.readline().split())
        x, y = B - int(b), int(a) - 1
        board[x][y] = i + 1
        if c == 'E': d = 0
        elif c == 'S': d = 1
        elif c == 'W': d = 2
        elif c == 'N': d = 3
        robots[i + 1] = (x, y, d)
    for _ in range(M):
        a, cmd, c = map(str, sys.stdin.readline().split())
        robot, number = int(a), int(c)
        if cmd == 'F':
            x, y, d = robots[robot]
            for _ in range(number):
                nx, ny = x + dx[d], y + dy[d]
                if 0 <= nx < B and 0 <= ny < A:
                    if board[nx][ny] == -1:
                        board[x][y] = -1
                        board[nx][ny] = robot
                        robots[robot] = (nx, ny, d)
                        x, y = nx, ny
                    else:
                        print(f'Robot {robot} crashes into robot {board[nx][ny]}')
                        exit(0)
                else:
                    print(f'Robot {robot} crashes into the wall')
                    exit(0)
        elif cmd == 'R':
            x, y, d = robots[robot]
            d = (d + number) % 4
            robots[robot] = (x, y, d)
        elif cmd == 'L':
            x, y, d = robots[robot]
            d = (d - number) % 4
            robots[robot] = (x, y, d)
    print('OK')



