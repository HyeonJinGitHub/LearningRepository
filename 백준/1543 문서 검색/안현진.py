if __name__ == '__main__':
    S = input()
    find_s = input()
    res = 0
    while True:
        tmp = S.find(find_s)
        if tmp == -1: break
        idx = tmp + len(find_s)
        S = S[idx:]
        res += 1
    print(res)
