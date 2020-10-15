import sys

def solution(board, chess_map, chess):
    dx = [0, 0, -1, 1]
    dy = [1, -1, 0, 0]
    time = 0

    while True:
        time += 1
        if time > 1000:
            print(-1)
            return
        for chess_num in range(1, K + 1):
            x, y, z = chess[chess_num]
            nx, ny = x + dx[z], y + dy[z]

            if nx < 0 or nx >= N or ny < 0 or ny >= N or board[nx][ny] == 2:
                if 0 <= z <= 1:
                    nz = (z + 1) % 2
                else:
                    nz = (z - 1) % 2 + 2
                chess[chess_num][2] = nz
                nx, ny = x + dx[nz], y + dy[nz]
                if nx < 0 or nx >= N or ny < 0 or ny >= N or board[nx][ny] == 2:
                    continue
            move_horse = []
            for i, key in enumerate(chess_map[x][y]):
                if key == chess_num:
                    move_horse.extend(chess_map[x][y][i:])
                    chess_map[x][y] = chess_map[x][y][:i]
                    break
            if board[nx][ny] == 1:
                move_horse.reverse()
            for horse in move_horse:
                chess_map[nx][ny].append(horse)
                chess[horse][:2] = [nx, ny]
            if len(chess_map[nx][ny]) >= 4:
                print(time)
                return
if __name__ == '__main__':
    N, K = map(int, sys.stdin.readline().split())
    board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    chess_map = [[[] for _ in range(N)] for _ in range(N)]
    chess = [[] for _ in range(K + 1)]

    for i in range(1, K + 1):
        x, y, z = map(int, sys.stdin.readline().split())
        chess[i] = [x - 1, y - 1, z - 1]
        chess_map[x - 1][y - 1].append(i)
    solution(board, chess_map, chess)







# import sys
# from collections import defaultdict
#
# def solution(board, horse_dict, location_dict):
#     dx = [0, 0, -1, 1]
#     dy = [1, -1, 0, 0]
#     time = 0
#     while True:
#         time += 1
#         if time > 1000:
#             print(-1)
#             return
#         for i in range(1, K + 1):
#             x, y, d = horse_dict[i][0]
#             nx, ny = x + dx[d], y + dy[d]
#             #print(f'{i}번말 : {x, y}  {i}번말 이동: {nx, ny}')
#
#             if 0 <= nx < N and 0 <= ny < N:
#                 if board[nx][ny] == 0:
#                     for k in range(len(location_dict[(x, y)])):
#                         if location_dict[(x, y)][k] == i:
#                             move_horse = location_dict[(x, y)][k:]
#                             location_dict[(x, y)] = location_dict[(x, y)][:k]
#                             location_dict[(nx, ny)].extend(move_horse)
#                             if len(location_dict[(nx, ny)]) >= 4:
#                                 print(time)
#                                 return
#                             for horse in move_horse:
#                                 tx, ty, nd = horse_dict[horse][0]
#                                 del horse_dict[horse]
#                                 horse_dict[horse].append((nx, ny, nd))
#                             break
#                 elif board[nx][ny] == 1:
#                     #print(f'{i}번말 : {x, y}  {i}번말 이동: {nx, ny}')
#                     for k in range(len(location_dict[(x, y)])):
#                         #print(f'빨간색에 들어갈 말: {location_dict[(x, y)][k]}')
#                         if location_dict[(x, y)][k] == i:
#                             move_horse = location_dict[(x, y)][k:]
#                             #print(f'reverse 전 말: {move_horse}')
#                             move_horse.reverse()
#                             #print(f'reverse 후 말: {move_horse}')
#                             location_dict[(x, y)] = location_dict[(x, y)][:k]
#                             location_dict[(nx, ny)].extend(move_horse)
#                             if len(location_dict[(nx, ny)]) >= 4:
#                                 print(time)
#                                 return
#                             for horse in move_horse:
#                                 # print(horse)
#                                 tx, ty, nd = horse_dict[horse][0]
#                                 del horse_dict[horse]
#                                 horse_dict[horse].append((nx, ny, nd))
#                             break
#                 elif board[nx][ny] == 2:
#                     if d == 0: d = 1
#                     elif d == 1: d = 0
#                     elif d == 2: d = 3
#                     elif d == 3: d = 2
#                     del horse_dict[i]
#                     horse_dict[i].append((x, y, d))
#                     nnx, nny = x + dx[d], y + dy[d]
#                     if 0 <= nnx < N and 0 <= nny < N:
#                         if board[nnx][nny] == 0:
#                             for k in range(len(location_dict[(x, y)])):
#                                 if location_dict[(x, y)][k] == i:
#                                     move_horse = location_dict[(x, y)][k:]
#                                     location_dict[(x, y)] = location_dict[(x, y)][:k]
#                                     location_dict[(nnx, nny)].extend(move_horse)
#                                     if len(location_dict[(nnx, nny)]) >= 4:
#                                         print(time)
#                                         return
#                                     for horse in move_horse:
#                                         tx, ty, nd = horse_dict[horse][0]
#                                         del horse_dict[horse]
#                                         horse_dict[horse].append((nnx, nny, nd))
#                                     break
#                         elif board[nnx][nny] == 1:
#                             for k in range(len(location_dict[(x, y)])):
#                                 if location_dict[(x, y)][k] == i:
#                                     move_horse = location_dict[(x, y)][k:]
#                                     move_horse.reverse()
#                                     location_dict[(x, y)] = location_dict[(x, y)][:k]
#                                     location_dict[(nnx, nny)].extend(move_horse)
#                                     if len(location_dict[(nnx, nny)]) >= 4:
#                                         print(time)
#                                         return
#                                     for horse in move_horse:
#                                         tx, ty, nd = horse_dict[horse][0]
#                                         del horse_dict[horse]
#                                         horse_dict[horse].append((nnx, nny, nd))
#                                     break
#                         elif board[nnx][nny] == 2:
#                             if d == 0: d = 1
#                             elif d == 1: d = 0
#                             elif d == 2: d = 3
#                             elif d == 3: d = 2
#                             del horse_dict[i]
#                             horse_dict[i].append((x, y, d))
#                     else:
#                         if d == 0: d = 1
#                         elif d == 1: d = 0
#                         elif d == 2: d = 3
#                         elif d == 3: d = 2
#                         del horse_dict[i]
#                         horse_dict[i].append((x, y, d))
#             else:
#                 if d == 0: d = 1
#                 elif d == 1: d = 0
#                 elif d == 2: d = 3
#                 elif d == 3: d = 2
#                 del horse_dict[i]
#                 horse_dict[i].append((x, y, d))
#                 nx, ny = x + dx[d], y + dy[d]
#                 if 0 <= nx < N and 0 <= ny < N:
#                     if board[nx][ny] == 0:
#                         for k in range(len(location_dict[(x, y)])):
#                             if location_dict[(x, y)][k] == i:
#                                 move_horse = location_dict[(x, y)][k:]
#                                 location_dict[(x, y)] = location_dict[(x, y)][:k]
#                                 location_dict[(nx, ny)].extend(move_horse)
#                                 if len(location_dict[(nx, ny)]) >= 4:
#                                     print(time)
#                                     return
#                                 for horse in move_horse:
#                                     tx, ty, nd = horse_dict[horse][0]
#                                     del horse_dict[horse]
#                                     horse_dict[horse].append((nx, ny, nd))
#                                 break
#                     elif board[nx][ny] == 1:
#                         for k in range(len(location_dict[(x, y)])):
#                             if location_dict[(x, y)][k] == i:
#                                 move_horse = location_dict[(x, y)][k:]
#                                 move_horse.reverse()
#                                 location_dict[(x, y)] = location_dict[(x, y)][:k]
#                                 location_dict[(nx, ny)].extend(move_horse)
#                                 if len(location_dict[(nx, ny)]) >= 4:
#                                     print(time)
#                                     return
#                                 for horse in move_horse:
#                                     tx, ty, nd = horse_dict[horse][0]
#                                     del horse_dict[horse]
#                                     horse_dict[horse].append((nx, ny, nd))
#                                 break
#                     elif board[nx][ny] == 2:
#                         if d == 0: d = 1
#                         elif d == 1: d = 0
#                         elif d == 2: d = 3
#                         elif d == 3: d = 2
#                         del horse_dict[i]
#                         horse_dict[i].append((x, y, d))
#                 else:
#                     print('불가능')
#         # for t in range(N):
#         #     for t1 in range(N):
#         #         print(location_dict[(t, t1)], end=' ')
#         #     print()
#         # print()
#
# if __name__ == '__main__':
#     N, K = map(int, sys.stdin.readline().split())
#     board = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
#     horse_dict = defaultdict(lambda :[])
#     location_dict = defaultdict(lambda :[])
#     for i in range(1, K + 1):
#         x, y, d = map(int, sys.stdin.readline().split())
#         location_dict[(x - 1, y - 1)].append(i)
#         horse_dict[i].append((x - 1, y - 1, d - 1))
#     solution(board, horse_dict, location_dict)
#
#
# # from collections import defaultdict
# # a = defaultdict(lambda :[])
# # a[1].append((1,2,3))
# # print(a[1])
# # [(b, c, d)] = a[1]

