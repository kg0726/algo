# 델타이동 좌하, 우하, 우상, 좌상
dr = [1, 1, -1, -1]
dc = [-1, 1, 1, -1]
# 1. 시작 지점을 어떻게 정할 것인가?
# -> 처음부터 끝까지 완전탐색?
# 2. 종료 지점을 어떻게 정할 것인가?

def make_rhombus(r, c, k):
    # 출발 지점에서 k만큼 위로 올라감(r - k)
    r = r - (k - 1)
    # 해당 위치에서 마름모 시작
    if 0 <= r < N:
        visit[r][c] = True

    # 꼭짓점 위치를 저장할 변수
    edge_r = 0
    edge_c = 0
    # 4방향으로 이동
    for i in range(4):
        for j in range(1, k):
            nr = r + dr[i] * j
            nc = c + dc[i] * j
            if 0 <= nr < N and 0 <= nc < N:
                visit[nr][nc] = True
            edge_r = nr
            edge_c = nc

        r = edge_r
        c = edge_c

# -> 모든 집이 선택될 때 부터 시작해서 한 집씩 줄여가며 가장 많은 집이 선택되는 경우를 탐색
def calculate_fee(r, c, k):
    max_protect = 0
    # 첫 비용은 1
    cost = 1
    while max_profit >= cost:
        # 마름모 그리기
        make_rhombus(r, c, k)
        # 몇개의 집이 방범 대상으로 포함되었는지 확인
        protect_house = 0
        for target_r in range(N):
            for target_c in range(N):
                # 현재 위치가 집이고, 방범구역일 경우
                if arr[target_r][target_c] == 1:
                    if visit[target_r][target_c] == True:
                        protect_house += 1
        # 비용 확인
        current_profit = protect_house * M
        # 손해가 발생하지 않는다면 최대값 비교 갱신
        if current_profit >= cost:
            max_protect = max(protect_house, max_protect)

        # 방범 구역 넓혀주기
        k += 1
        # 비용은 최대 수익보다 작거나 같아야함
        cost = k * k + (k - 1) * (k - 1)

    return max_protect



T = int(input())
for tc in range(1, 1 + T):
    N, M = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]


    # 주어진 배열을 순회하며 최대 수익을 구함
    house = 0
    for r in range(N):
        for c in range(N):
            if arr[r][c] == 1:
                house += 1
    # 최대수익
    max_profit = house * M

    max_house = 0
    # 배열을 순회하며 방범 구역을 탐색
    for r in range(N):
        for c in range(N):
            # 같은 크기의 방문 기록표 초기화
            visit = [[False] * N for _ in range(N)]
            result = calculate_fee(r, c, 1)
            # 최대값 비교 및 갱신
            max_house = max(result, max_house)

    print(f'#{tc} {max_house}')