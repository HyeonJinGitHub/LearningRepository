if __name__ == '__main__':
    S = input()
    flag = False
    res = []
    tmp = []
    for ch in S:
        if ch == '<':
            flag = True
            if tmp:
                tmp.reverse()
                for t in tmp:
                    res.append(t)
                tmp.clear()
            res.append(ch)
            continue
        elif ch == '>':
            flag = False
            res.append(ch)
            continue
        else:
            if flag:
                res.append(ch)
            else:
                if ch != ' ':
                    tmp.append(ch)
                else:
                    tmp.reverse()
                    for t in tmp:
                        res.append(t)
                    res.append(' ')
                    tmp.clear()
    if tmp:
        tmp.reverse()
        for t in tmp:
            res.append(t)
    print(''.join(res))