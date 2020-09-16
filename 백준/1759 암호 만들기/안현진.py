def check(password):
    ja = 0
    mo = 0
    for x in password:
        if x in 'aeiou':
            mo += 1
        else:
            ja += 1
    return ja >= 2 and mo >= 1

def solution(n, alpha, password, idx):
    # 불가능한 경우
    if idx == len(alpha):
        return
    # 정답을 찾은 경우
    if len(password) == n:
        if check(password):
            print(password)
        return
    # 다음 경우 호출
    solution(n, alpha, password + alpha[idx], idx+1)
    solution(n, alpha, password, idx + 1)


if __name__ == '__main__':
    L, C = list(map(int, input().split()))
    arr = list(map(str, input().split()))
