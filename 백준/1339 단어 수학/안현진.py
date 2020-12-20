if __name__ == '__main__':
    N = int(input())
    alpha = [0] * 26
    word_list = []
    for _ in range(N):
        word_list.append(input())
    for word in word_list:
        size = len(word)
        for idx, ch in enumerate(word):
            alpha[ord(ch) - ord('A')] += 10 ** (size - (idx + 1))
    alpha.sort(reverse=True)
    n = 9
    ans = 0
    for y in alpha:
        if y == 0: break
        ans += y * n
        n -= 1
    print(ans)