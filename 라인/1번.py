def solution(boxes):
    box_count = len(boxes)
    lists = []
    answer = 0
    for i in range(len(boxes)):
        for j in range(len(boxes[i])):
            lists.append(boxes[i][j])
    lists.sort()
    count = {}
    tmp_count = 0
    for t in lists:
        try:
            count[t] += 1
        except:
            count[t] = 1
    for key in count.keys():
        if count[key] % 2 != 0:
            if count[key] > 2:
                tmp_count += count[key] // 2
            answer += count[key] % 2
        else:
            tmp_count += count[key] // 2
    if answer > box_count - tmp_count:
        answer = box_count - tmp_count
    return answer


if __name__ == '__main__':
    print(solution([[1, 2], [2, 1], [3, 3], [4, 5], [5, 6], [7, 8]]))
    print(solution([[1, 2], [3, 4], [5, 6]]))
    print(solution([[1, 2], [2, 3], [3, 1]]))
    print(solution([[1, 1], [1, 2], [4, 6], [5, 4]]))
    print(solution([[2,5], [3, 2], [178, 41], [99, 81], [6, 3], [5, 12]]))