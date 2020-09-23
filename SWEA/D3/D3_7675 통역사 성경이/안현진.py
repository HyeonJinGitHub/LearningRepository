if __name__ == '__main__':
    T = int(input())
    for i in range(T):
        N = int(input())
        count = 0
        idx_start = 0
        idx_end = 0
        res = 0
        result = ''
        s = input()
        for j in range(len(s)):
            idx_start = idx_end
            if s[j] == '?' or s[j] == '!' or s[j] == '.':
                count += 1
                res = 0
                idx_end = j + 1

            if idx_start < idx_end:
                tmp = s[idx_start:idx_end]
                words = tmp.split()
                for k in range(len(words)):
                    if 'A' <= words[k][0] <= 'Z':
                        if len(words[k]) == 1: res += 1
                        else:
                            ok = True
                            end_idx = 0
                            for l in range(1, len(words[k])):
                                if not ('a' <= words[k][l] <= 'z'):
                                    ok = False
                                    end_idx = l
                                    break
                            if end_idx == len(words[k]) - 1 and words[k][end_idx] == '.' or words[k][end_idx] == '!' or words[k][end_idx] == '?':
                                ok = True
                            if ok:
                                res += 1
                result += str(res) + ' '
        print(f'#{i + 1} {result}')