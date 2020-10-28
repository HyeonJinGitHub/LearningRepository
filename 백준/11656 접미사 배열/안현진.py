if __name__ == '__main__':
    S = input()
    tmp = []
    for i in range(len(S)):
        tmp.append(S[i:])
    tmp.sort()
    for res in tmp:
        print(res)