from collections import deque

if __name__ == '__main__':
    S = input()
    q = deque()

    for i in range(len(S)):
        if S[i] != ')':
            if S[i] == '(':
                q.append(S[i])
            else:
                q.append(int(S[i]))
        else:
            length = 0
            while q:
                v = q.pop()
                if v == '(':
                    break
                else:
                    if v >= 10:
                        length += v - 10
                    else:
                        length += 1
            length = length * q[-1] + 10
            q.pop()
            q.append(length)
    result = 0
    for i in range(len(q)):
        if q[i] >= 10:
            result += q[i] - 10
        else:
            result += 1
    print(result)