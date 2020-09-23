import operator

lists = []
def solution(orders, course):
    tmp_2 = []
    for i in range(len(orders)):
        tmp_1 = []
        for j in range(len(orders[i])):
            tmp_1.append(orders[i][j])
        tmp_2.append(tmp_1)
    for i in range(len(tmp_2)):
        tmp_2[i].sort()
        printAllSubset(tmp_2[i])
    arr = []
    for i in range(len(lists)):
        t = ''
        for j in range(len(lists[i])):
            t += str(lists[i][j])
        arr.append(t)
    answer = []
    for k in range(len(course)):
        arr1 = []

        for i in range(len(arr)):
            if len(arr[i]) == course[k]:
                arr1.append(arr[i])
        if len(arr1) == 0:
            continue
        count = {}
        for t in arr1:
            try:
                count[t] += 1
            except:
                count[t] = 1
        sorted_dict = dict(sorted(count.items(), key=operator.itemgetter(1)))
        sorted_lists = list(sorted_dict)
        max_value = sorted_dict[sorted_lists[len(sorted_lists) - 1]]
        for key, v in sorted_dict.items():
            if max_value != 1:
                if v == max_value:
                    answer.append(key)
    answer.sort()
    lists.clear()
    return answer

def printAllSubset(A):
    printAllSubsetHelper(A, 0)

def printAllSubsetHelper(A, index):
    if index == len(A):
        if len(A) > 1:
            lists.append(A)
        return
    printAllSubsetHelper(A[:], index + 1)
    A.pop(index)
    printAllSubsetHelper(A[:], index)

if __name__ == '__main__':
    print(solution(["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"],[2,3,4]))
    #print(solution(["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"],[2,3,5]))
    #print(solution(["XYZ", "XWY", "WXA"],[2,3,4]))