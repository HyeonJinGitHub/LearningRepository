import sys

w, h = map(int, sys.stdin.readline().split())
p, q = map(int, sys.stdin.readline().split())
t = int(sys.stdin.readline())

x = (p + t) // w
y = (q + t) // h

if x % 2 == 0:
    p = (p + t) % w
else:
    p = w - ((p + t) % w)
if y % 2 == 0:
    q = (q + t) % h
else:
    q = h - ((q + t) % h)
print(p, q)