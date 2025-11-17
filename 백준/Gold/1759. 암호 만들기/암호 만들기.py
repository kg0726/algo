# 자음 모음
gather = 'aeiou'

def dfs(depth, pre_alpha, pw, gather_count, consonant_count):
    # 재귀의 깊이가 L과 같으면 종료
    if depth == L:
        # 자음 모음 제한 확인
        if gather_count >= 1 and consonant_count >= 2:
            result.append(pw)
        return

    # 알파벳 중 조건에 맞는 하나를 선택
    for i in range(len(arr)):
        # 방문하지 않았고, 이전 알파벳보다 나중의 알파벳이면 추가
        if not visited[i] and arr[i] > pre_alpha:
            visited[i] = True
            if arr[i] in gather:
                dfs(depth + 1, arr[i], pw + arr[i], gather_count + 1, consonant_count)
            else:
                dfs(depth + 1, arr[i], pw + arr[i], gather_count, consonant_count + 1)
            # 백트래킹
            visited[i] = False

L, C = map(int, input().split())
arr = list(input().split())

result = []

# 주어진 문자열 배열을 순회하며 어떤 문자열부터 시작할지 결정
for i in range(len(arr)):
    # 방문 기록 초기화
    visited = [False] * C
    visited[i] = True
    # 재귀호출
    if arr[i] in gather:
        dfs(1, arr[i], arr[i], 1, 0)
    else:
        dfs(1, arr[i], arr[i], 0, 1)

result.sort()
# 출력 형식
for i in result:
    print(i)