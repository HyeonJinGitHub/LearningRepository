import sys

if __name__ == '__main__':
    for _ in range(int(input())):
        s = sys.stdin.readline().rstrip()
        left, right = [], []

        for ch in s:
            if ch == '<':
                if not left: continue
                right.append(left.pop())
            elif ch == '>':
                if not right: continue
                left.append(right.pop())
            elif ch == '-':
                if not left: continue
                left.pop()
            else:
                left.append(ch)
        right.reverse()
        left.extend(right)
        print(''.join(left))