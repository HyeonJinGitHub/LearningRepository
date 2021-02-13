import sys

if __name__ == '__main__':
    N, M = map(int, sys.stdin.readline().split())
    arr = list(map(int, sys.stdin.readline().split()))
    negative, positive = [], []
    for y in arr:
        if y < 0: negative.append(y)
        else: positive.append(y)
    negative.sort(); positive.sort(reverse=True)

    distance = []
    for i in range(len(negative) // M):
        distance.append(abs(negative[i * M]))
    if len(negative) % M > 0:
        distance.append(abs(negative[(len(negative) // M) * M]))
    for i in range(len(positive) // M):
        distance.append(positive[i * M])
    if len(positive) % M > 0:
        distance.append(positive[(len(positive) // M) * M])
    distance.sort()
    ans = distance.pop()
    ans += 2 * sum(distance)
    print(ans)
