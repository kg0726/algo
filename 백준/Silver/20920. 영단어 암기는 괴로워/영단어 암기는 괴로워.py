import sys
# sys.stdin = open("input.txt")

N, M = map(int, sys.stdin.readline().split())

arr = []
# 중복 및 나온 개수 카운트 딕셔너리
count_dict = {}

for _ in range(N):
    word = sys.stdin.readline().rstrip()
    # 짧은 단어는 넘어감
    if len(word) < M:
        continue

    # 영단어 빈도수 측정
    count_dict[word] = count_dict.get(word, 0) + 1


result = sorted(count_dict.keys(), key=lambda x: (-count_dict[x], -len(x), x))

for i in result:
    print(i)

