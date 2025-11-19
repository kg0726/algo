# 문자열 입력
A = input()
B = input()

# 문자열 길이
a = len(A)
b = len(B)

# dp 배열 생성
# 왼쪽 위를 참조해야 하는 경우가 있으므로 1만큼 패딩하여 생성
dp = [[0] * (b + 1) for _ in range(a + 1)]

result = 0
# 문자열을 2차원 순회
for i in range(1, a + 1):
    for j in range(1, b + 1):
        # 1. 문자가 같은 경우
        # 공통 부분 문자열의 마지막 문자로 사용이 가능함
        # 즉, 해당 문자를 사용하지 않았던 시점(i - 1, j - 1)의 dp값에서 + 1을 해주면 됨
        if A[i - 1] == B[j - 1]:
            dp[i][j] = dp[i - 1][j - 1] + 1
            result = max(result, dp[i][j])
        # 2. 문자가 다른 경우
        # 공통 부분 문자열의 마지막 문자로서 사용이 불가능함
        # 즉, 해당 위치의 문자는 공통 부분 문자열에 해당될 수 없음
        else:
            dp[i][j] = 0

print(result)