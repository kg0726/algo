N, K = map(int, input().split())
# 0번 인덱스 사용 안함
weight = [0] * (N + 1)
value = [0] * (N + 1)
for i in range(1, N + 1):
    weight[i], value[i] = map(int, input().split())

# 1만큼 패딩하여 dp배열 생성
dp = [[0] * (K + 1) for _ in range(N + 1)]

result = 0
# dp 배열을 순회함 i: 고려할 물건 번호 ex) j == 3이라면 1 ~ 3 까지의 물건을 고려)
for i in range(1, N + 1):
    # j: 허용할 무게
    for j in range(1, K + 1):
        # 1. 물건을 넣을 수 없는 경우(무게 초과)
        if j < weight[i]:
            # 이전 무게까지 고려한 값을 그대로 사용
            dp[i][j] = dp[i - 1][j]
        # 2. 물건을 넣을 수 있는 경우
        # 2-1. 물건을 안넣기로 결정: 이전 무게까지 고려한 값을 그대로 사용
        # 2-2. 물건을 넣기로 결정: 해당 물건을 넣기 전의 무게와 물건 번호 값에 현재 물건의 가치를 더함
        # 2-3. 둘 중 최대값
        else:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
            result = max(result, dp[i][j])

print(result)