N = int(input())

color = [list(map(int, input().split())) for _ in range(N)]

# dp 배열 정의

# 행: 집의 번호
# 열: 색깔
# 즉dp[i][c] 는 i번째 집에서 c라는 색깔을 선택했을 때의 최적해
dp = [[0] * 3 for _ in range(N)]
# 첫번째 집은 아무런 제약조건이 없으므로 그 색깔의 비용이 최적해
for i in range(3):
    dp[0][i] = color[0][i]

# 두번째 행부터 순회
for r in range(1, N):
    for c in range(3):
        if c == 0:
            dp[r][c] = min(dp[r - 1][1], dp[r - 1][2]) + color[r][c]
        elif c == 1:
            dp[r][c] = min(dp[r - 1][0], dp[r - 1][2]) + color[r][c]
        else:
            dp[r][c] = min(dp[r - 1][0], dp[r - 1][1]) + color[r][c]

print(min(dp[N - 1]))