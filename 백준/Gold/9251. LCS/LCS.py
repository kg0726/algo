# 입력 시작
first = input()
second = input()

f = len(first)
s = len(second)

# 2차원 dp배열 생성
dp = [[0] * (s + 1) for _ in range(f + 1)]

# f와 s를 2차원 순회
for i in range(1, f + 1):
    for j in range(1, s + 1):
        # 순회 중 i, j의 알파벳이 같은 경우
        # 현재 알파벳을 lcs의 마지막 알파벳으로 사용할 수 있다는 뜻
        # 즉, lcs의 최장 거리가 현재 시점에서 1 증가한다는 뜻으로
        # 현재 알파벳을 사용하지 않았을 최장거리를 가지고 있는
        # dp[i - 1][j - 1]에 1을 더해 현재 위치에 할당함
        if first[i - 1] == second[j - 1]:
            dp[i][j] = dp[i - 1][j - 1] + 1
        # 알파벳이 다른 경우
        # 현재 알파벳을 lcs의 마지막 알파벳으로 사용할 수 없다는 뜻
        # 즉, lcs의 최장거리에는 변함이 없음
        # 그렇다면 현재 선택된 두 알파벳 중 하나를 사용하지 않았을 시점의 최장거리를 할당
        elif first[i - 1] != second[j - 1]:
            dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

print(dp[f][s])
