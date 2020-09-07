import base64

if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        S = input()
        s1 = S.encode('UTF-8')
        d = base64.b64decode(s1)
        s2 = d.decode('UTF-8')
        print(f'#{i+1} {s2}')