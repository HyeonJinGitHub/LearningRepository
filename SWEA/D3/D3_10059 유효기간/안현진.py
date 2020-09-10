if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = input()
        fir = int(N[:2])
        sec = int(N[2:])
        if fir > 12 or fir == 0:
            if sec > 12 or sec == 0:
                print(f'#{i+1} NA')
            else:
                print(f'#{i+1} YYMM')
        else:
            if sec > 12 or sec == 0:
                print(f'#{i+1} MMYY')
            else:
                print(f'#{i+1} AMBIGUOUS')