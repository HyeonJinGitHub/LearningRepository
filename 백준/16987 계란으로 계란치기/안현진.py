import sys

def solution(target):
    if target == N: # 처음 잡은 알이 마지막 달걀일경우
        res = 0
        for i in range(N):
            if duration[i] <= 0: # 깨진 달걀 갯수 세기
                res += 1
        return res
    if duration[target] <= 0:# 달걀이 꺠져있으면 그 오른쪽 달걀 선택
        return solution(target + 1)
    check = False
    result = 0

    for i in range(N):# 하나의 달걀을 잡은 후 후려칠 다른 달걀 선택
        if target == i: continue # 같은 달걀이면 패스
        if duration[i] <= 0: continue # 깨진 달걀이면 패스
        # 달걀 안깨졌으면
        check = True

        duration[target] -= weight[i] # 잡아든 달걀의 내구도 감소
        duration[i] -= weight[target] # 후려친 달걀의 내구도 감소

        result = max(result, solution(target + 1)) # 재귀를 통해 다음 달걀 선택

        # 다른 경우 파악을 위해 내구도 원상태로 돌려놓음
        duration[target] += weight[i]
        duration[i] += weight[target]
    # 달걀을 들었는데 다른 모든 달걀이 깨져있는 경우?
    if not check:
        return solution(target + 1)

    return result

if __name__ == '__main__':
    N = int(input())
    duration, weight = [], []
    for _ in range(N):
        d, w = map(int, sys.stdin.readline().split())
        duration.append(d)
        weight.append(w)
    print(solution(0))
