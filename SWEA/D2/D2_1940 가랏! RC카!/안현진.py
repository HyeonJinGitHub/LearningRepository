if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        cmd = int(input())
        speed = 0
        dis = 0
        for j in range(cmd):
            lis = list(map(int, input().split()))
            if lis[0] == 1:
                speed += lis[1]
            elif lis[0] == 2:
                speed -= lis[1]
                if speed < 0:
                    speed = 0
            dis += speed
        print(f'#{i+1} {dis}')