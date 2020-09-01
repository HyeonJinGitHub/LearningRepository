import operator
# 리스트 만들기
list1 = [32.5, 52.7, 63.0, 99.83, 11.21]
# zip() 을 이용해 1:32.5, 2:52.7 등의 dictionary 형태로 만들기 & dict() 사용
tmp_dict = dict(zip(range(1, len(list1)+1), list1))
# tmp_dict.items() 를 이용해 키, 값 모두 가져오고, 두 번쨰 인자인 key=operator.itemgetter(1) 를 이용해 values 를 기준으로 정렬
# 두 번째 인자에 key=operator.itemgetter(0) 을 넣으면 keys 를 기준으로 정렬
# key=operator.itemgetter() 를 사용하려면 import operator 필요
sorted_dict = dict(sorted(tmp_dict.items(), key=operator.itemgetter(1)))
# 아래와 같이 [::-1] 을 붙이면 values 를 기준으로 내림차순 정렬
# sorted_dict = dict(sorted(tmp_dict.items(), key=operator.itemgetter(1))[::-1])
print(sorted_dict)