def find_n_queen(r):
    global result
    # 끝까지 한바퀴 돌았다면 종료
    if r == N:
        result += 1
        return
    
    # 현재 고려중인 r의 칸에 하나의 퀸을 놓아야 함
    # 현재 칸에서 놓을 수 있는 c를 탐색
    for i in range(N):
        if not visited_c[i] and not visited_plus[r + i] and not visited_minus[r - i + N - 1]:
            # 놓고 넘어감
            visited_c[i], visited_plus[r + i], visited_minus[r - i + N - 1] = True, True, True
            # 재귀 호출
            find_n_queen(r + 1)
            # 백트래킹
            visited_c[i], visited_plus[r + i], visited_minus[r - i + N - 1] = False, False, False


N = int(input())

# 방문 배열 생성
visited_r = [False] * N
visited_c = [False] * N
# 합대각
visited_plus = [False] * ((N * 2) - 1)
# 차대각
visited_minus = [False] * ((N * 2) - 1)

# 결과 초기화
result = 0

find_n_queen(0)
print(result)