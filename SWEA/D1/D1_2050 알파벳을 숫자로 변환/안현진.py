S = input()
res = []
for i in range(0,len(S)):
    res.append(str(ord(S[i]) - 64))
print(" ".join(res))