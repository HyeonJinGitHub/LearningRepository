import sys

if __name__ == '__main__':
    N = int(input())
    room = []
    for _ in range(N):
        s, e = map(int, sys.stdin.readline().split())
        room.append((e, s))
    room.sort()
    ans, now = 0, 0
    for y in room:
        end, start = y[0], y[1]
        if now <= start:
            now = end
            ans += 1
    print(ans)