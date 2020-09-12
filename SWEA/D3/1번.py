import re

def solution(new_id):
    tmp = new_id
    step_1 = tmp.lower()
    p = re.compile(r"""
        [0-9]+ |
        [a-z]+ |
        [._\-]
    """, re.VERBOSE)
    tmp_2 = re.findall(p, step_1)
    step_2 = ''
    for i in range(len(tmp_2)):
        step_2 += tmp_2[i]
    for _ in range(10):
        step_2 = step_2.replace('..', '.')
    step_3 = step_2
    step_4 = step_3.strip('.')
    if step_4 == '':
        step_5 = 'a'
    else: step_5 = step_4
    if len(step_5) >= 16: step_6 = step_5[:15]
    else: step_6 = step_5
    step_6 = step_6.rstrip('.')
    step_7 = step_6
    if len(step_7) <= 2:
        for _ in range(5):
            step_7 = step_7 + step_7[len(step_6) - 1]
            if len(step_7) >= 3:
                break
    answer = step_7
    return answer

if __name__ == '__main__':
    print(solution("...!@BaT#*..y.abcdefghijklm.acaacacacacac"))
    print(solution(	"z-+.^."))
    print(solution("=.="))
    print(solution("123_.def"))
    print(solution("abcdefghijklmn.p"))
    '''
    t = ''
    for i in range(999):
        t += '.'
    print(solution(t))
    '''
