if __name__ == '__main__':
    S = input()
    cnt = 0
    for i in range(len(S)):
        if S[i] == 'P':
            if S[i - 1] == 'A': continue
            cnt += 1
        else:
            if cnt >= 2 and i + 1 < len(S) and S[i + 1] == 'P':
                cnt -= 1
            else:
                print('NP')
                exit(0)
    print('PPAP' if cnt == 1 else 'NP')