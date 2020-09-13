def solution(ball, order):
    ball_str = ''
    answer = []
    t = []
    for i in range(len(ball)):
        ball_str += str(ball[i])

    for i in order:
        a = ball_str.find(str(i))
        if a == 0 or a == len(ball_str) - len(str(i)):
            ball_str = ball_str.replace(str(i), '')
            answer.append(i)
        else:
            t.append(i)
        idx = 0
        while idx < len(t):
            a = ball_str.find(str(t[idx]))
            if a == 0 or a == len(ball_str) - len(str(t[idx])):
                ball_str = ball_str.replace(str(t[idx]), '')
                answer.append(t[idx])
                t.pop(idx)
                idx = 0
            else:
                idx += 1
    return answer

if __name__ == '__main__':
    print(solution([1, 2, 3, 4, 5, 6], [6, 2, 5, 1, 4, 3]))
    print(solution([1, 2, 3, 4, 5, 6], [3, 4, 2, 1, 5, 6]))
    print(solution([11, 2, 9, 13, 24], [9, 2, 13, 24, 11]))
