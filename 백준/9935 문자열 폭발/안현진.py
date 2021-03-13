import sys

if __name__ == '__main__':
    original_str = list(map(str, sys.stdin.readline().rstrip()))
    boom_str = list(map(str, sys.stdin.readline().rstrip()))

    boom_ch = boom_str[-1]
    stack = []
    for ch in original_str:
        stack.append(ch)
        if ch == boom_ch and len(stack) >= len(boom_str):
            tmp = []
            for _ in range(len(boom_str)):
                tmp.append(stack.pop())
            tmp.reverse()
            if tmp != boom_str:
                stack.extend(tmp)
    print(''.join(map(str, stack)) if stack else 'FRULA')