import sys

if __name__ == '__main__':
    mem = [0] * 32
    while True:
        try:
            for i in range(32):
                mem[i] = int(sys.stdin.readline(), 2)
        except: exit()

        PC = 0
        acc = 0

        while True:
            cmd = mem[PC] // 32
            val = mem[PC] % 32

            if cmd == 7: break
            PC = (PC + 1) % 32
            if cmd == 0:
                mem[val] = acc
            elif cmd == 1:
                acc = mem[val]
            elif cmd == 2:
                if acc == 0:
                    PC = val
            elif cmd == 4:
                acc = (acc + 255) % 256
            elif cmd == 5:
                acc = (acc + 1) % 256
            elif cmd == 6:
                PC = val
        res = format(acc, 'b')
        res = res.zfill(8)
        print(res)
