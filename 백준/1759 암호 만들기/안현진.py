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
    # 정답을 찾은 경우
    if len(password) == n:
        if check(password):
            print(password)
        return
    # 불가능한 경우
    if idx == len(alpha):
        return
    # 다음 경우 호출
    # 다음 알파벳 사용
    solution(n, alpha, password + alpha[idx], idx + 1)
    # 다음 알파벳 사용 X
    solution(n, alpha, password, idx + 1)

if __name__ == '__main__':
    R, C = list(map(int, input().split()))
    arr = list(map(str, input().split()))
    arr.sort()
    solution(R, arr, '', 0)