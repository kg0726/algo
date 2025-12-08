N, K = map(int, input().split())
arr = list(map(int, input().split()))

# 투포인터 설정
start = 0
end = 0

check = {}


answer = 0

# 종료조건: end가 끝까지 가면 종료
while end < N:
    if check.get(arr[end]) is None:
        check[arr[end]] = 1
    elif check[arr[end]] <= K:
        check[arr[end]] += 1

    if check.get(arr[end]) is not None and check[arr[end]] > K:
        check[arr[start]] -= 1
        start += 1


    if check[arr[end]] <= K:
        end += 1
        answer = max(answer, end - start)

print(answer)