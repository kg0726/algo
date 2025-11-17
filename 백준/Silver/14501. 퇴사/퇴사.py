# 1번 인덱스 사용하지 않음
N = int(input())
arr = [[0, 0]] + [(list(map(int, input().split()))) for _ in range(N)]
# dp 배열 생성(1번 인덱스 사용하지 않음)
dp = [0] * (N + 1)
# 입력받은 배열을 거꾸로 순회하며 dp 배열을 추가
for i in range(N, 0, -1):
    cost, income = arr[i]
    # 마지막 인덱스 조정
    if i == N:
        if i + cost > N + 1:
            dp[i] = 0
        elif i + cost == N + 1:
            dp[i] = income
        continue
    # 회사에 있는 기간을 벗어나면 상담을 할 수 없음
    if i + cost > N + 1:
        dp[i] = dp[i + 1]
        continue
    # 상담이 가능한 경우
    # 상담을 하지 않는 경우(다음날의 최댓값)와 상담을 하는 경우(상담을 하고 받는 금액 + 상담이 끝난 시점의 최댓값) 중 큰 값을 선택
    # 만약 상담이 N + 1번째 날에 끝난다면
    if i + cost == N + 1:
        dp[i] = max(dp[i + 1], income)
        continue
    dp[i] = max(dp[i + 1], income + dp[i + cost])

print(dp[1])
