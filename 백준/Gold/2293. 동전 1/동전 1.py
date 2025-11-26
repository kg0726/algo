n, k = map(int, input().split())

coins = [0] * n
for i in range(n):
    coins[i] = int(input())

# dp배열 생성 인덱스 0 ~ k까지
dp = [0] * (k + 1)
# 0원을 만드는 경우 == 아무것도 안쓰면 됨 즉, 1
dp[0] = 1

# 순서만 다른 것은 같은 경우로 처리하므로 코인을 먼저 순회해야함
for coin in coins:
    # 그 코인보다 같거나 큰 금액 ~ k 까지 순회
    for i in range(coin, k + 1):
        # i를 현재 허용된 금액이라고 한다면 dp[i]는 현재 금액에서 허용된 경우의 수의 합
        # 때문에 지금의 coin 값을 사용했을 때 dp[i]를 구하려면 지금의 coin값을 사용하기 이전의 경우의 수인
        # dp[i - coin]을 현재 dp[i]에 더해주면 됨
        dp[i] = dp[i] + dp[i - coin]

print(dp[k])