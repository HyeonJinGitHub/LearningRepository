import sys

if __name__ == '__main__':
    idx = 1
    while True:
        S = list(map(str, sys.stdin.readline().rstrip()))
        if '-' in S: break
        stack = []
        ans = 0
        for y in S:
            if y == '{':
                stack.append(y)
            else:
                if stack:
                    stack.pop()
                else:
                    ans += 1
                    stack.append('{')
        while stack:
            if len(stack) % 2 == 0:
                ans += 1
            stack.pop()
        print(f'{idx}. {ans}')
        idx += 1