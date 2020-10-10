import sys
from collections import defaultdict

def solution(board, A, tree_dict, K):
    global tree_count
    dx = [-1, -1, -1, 0, 0, 1, 1, 1]
    dy = [-1, 0, 1, -1, 1, -1, 0, 1]

    while K > 0:
        for i in range(N*N):
            r, c = i // N, i % N

            trees = tree_dict[(r, c)]
            nutrition = board[r][c]
            add_nutrition = 0

            if not trees:
                add_nutrition += A[r][c]
                add_nutrition += nutrition
                board[r][c] = add_nutrition
                continue
            tmp_tree = []
            while trees:
                tree_old = trees.pop()
                check = nutrition - tree_old
                if check >= 0:
                    nutrition -= tree_old
                    tmp_tree.append(tree_old + 1)
                else:
                    tree_count -= 1
                    add_nutrition += (tree_old // 2)
            trees.extend(tmp_tree)
            if len(trees) > 1:
                trees.sort(reverse=True)
            add_nutrition += A[r][c]
            add_nutrition += nutrition
            board[r][c] = add_nutrition
        for key, value in tree_dict.items():
            new_tree = 0
            if len(value) == 0:
                continue
            for i in value:
                if i < 5:
                    break
                if i % 5 == 0:
                    new_tree += 1
            if new_tree > 0:
                r, c = key
                for d in range(8):
                    nr, nc = r + dx[d], c + dy[d]
                    if 0 <= nr < N and 0 <= nc < N:
                        tree_count += new_tree
                        tree_dict[(nr, nc)].extend([1] * new_tree)
        K -= 1
    print(tree_count)


if __name__ == '__main__':
    N, M, K = map(int, sys.stdin.readline().split())
    board = [[5] * N for _ in range(N)]
    A = [[int(x) for x in sys.stdin.readline().split()] for _ in range(N)]
    tree_count = 0
    tree_dict = defaultdict(lambda :[])

    for _ in range(M):
        x, y, z = map(int, sys.stdin.readline().split())
        tree_dict[(x - 1, y - 1)].append(z)
        tree_count += 1
    solution(board, A, tree_dict, K)