# 입력 시작
N, M = map(int, input().split())
r, c, direction = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

# 상우하좌
dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]

# 4방향 안에 청소할게 있을 때 방향전환
need_clean = {
    0: 3,
    1: 0,
    2: 1,
    3: 2,
}
# 청소할게 없을 때 방향전환
no_clean = {
    0: 2,
    1: 3,
    2: 0,
    3: 1,
}

arr[r][c] = 2
result = 1

is_clean = False
while True:
    # 4방향 중 청소가 가능한 곳이 있었다면 바로 앞칸을 확인해본다.
    if is_clean:
        nr = r + dr[direction]
        nc = c + dc[direction]
        if 0 <= nr < N and 0 <= nc < M:
            if arr[nr][nc] == 0:
                result += 1
                arr[nr][nc] = 2
                r = nr
                c = nc

    # 청소 가능 여부 초기화
    is_clean = False
    # 4방향을 확인하며 청소할 곳이 있는지 확인
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]
        if 0 <= nr < N and 0 <= nc < M:
            # 청소가 가능한지 확인
            if arr[nr][nc] == 0:
                is_clean = True
                break
    # 4방향 안에 청소할 게 있었다면
    if is_clean:
        # 반시계 90도 회전
        direction = need_clean[direction]
    # 없었다면 뒤로 한칸
    else:
        back = no_clean[direction]
        nr = r + dr[back]
        nc = c + dc[back]
        if 0 <= nr < N and 0 <= nc < M:
            if arr[nr][nc] == 1:
                break
        else:
            break
        # 위 조건문을 통과했다면 뒤로 한칸 갈 수 있는 상태
        r = nr
        c = nc
print(result)