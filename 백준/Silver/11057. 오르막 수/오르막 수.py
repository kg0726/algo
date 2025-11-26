n = int(input())
# 가로 0 ~ 9 까지 세로 0 ~ n 까지 인덱스르 가지도록 2차원 배열 생성
dp = [[0] * 10 for _ in range(n + 1)]
# 0번째 인덱스를 모두 1로 패딩(공집합 고려)
for i in range(10):
    dp[0][i] = 1
for i in range (n + 1):
    dp[i][0] = 1

# 패딩되지 않은 dp 배열을 순회하며 해당 위치의 왼쪽, 위쪽 좌표의 합을 할당함
for i in range(1, n + 1):
    for j in range(1, 10):
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

print(dp[n][9] % 10007)