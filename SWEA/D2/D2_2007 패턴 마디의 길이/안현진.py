N = int(input())
for i in range(N):
    s = input()
    j = 0
    for k in range(1, len(s)):
        if s[j] == s[k]:
            j += 1
        else:
            j = 0
    print(f'#{i+1} {len(s) - j}')