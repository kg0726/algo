R, C, T = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(R)]
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

# 반시계
up_dr = [0, -1, 0, 1]
up_dc = [1, 0, -1, 0]
# 시계
down_dr = [0, 1, 0, -1]
down_dc = [1, 0, -1, 0]

def spread(arr):
    # 미세먼지의 확산이 이루어지는 함수
    # 미세먼지 좌표와 미세먼지 확산 규모를 담을 리스트
    dust_list = []
    # 주어진 배열을 순회하며 미세먼지를 찾는다.
    for r in range(R):
        for c in range(C):
            # 확산 가능한(5 이상인 미세먼지 발견) 미세먼지 발견
            if arr[r][c] >= 5:
                # 확산될 미세먼지
                dust = arr[r][c] // 5
                # 확산 될 방향 확인
                for i in range(4):
                    nr = r + dr[i]
                    nc = c + dc[i]
                    # 배열의 경계와 공기청정기인지 확인
                    if 0 <= nr < R and 0 <= nc < C and arr[nr][nc] != -1:
                        # 좌표, 확산규모 형식으로 리스트에 삽입
                        dust_list.append((nr, nc, dust))
                        # 확산될 만큼 현재 규모를 줄여준다.
                        arr[r][c] -= dust
    return dust_list

def air_fresh(up, down):
    # 위쪽 공기청정기(반시계 순환 시작)
    # 4방향 이동
    r, c = up
    # 오른쪽 한칸 가서 시작
    c += 1
    for i in range(4):
        # 벽에 닿을 때 까지 진행
        step = 1
        if i == 0:
            # 현재 미세먼지 수를 기억
            now_dust = arr[r][c]
            arr[r][c] = 0
        else:
            now_dust = last_dust
        while True:
            nr = r + up_dr[i] * step
            nc = c + up_dc[i] * step
            if 0 <= nr < R and 0 <= nc < C and arr[nr][nc] != -1:
                # 다음 미세먼지 수를 기억
                next_dust = arr[nr][nc]
                arr[nr][nc] = now_dust
                now_dust = next_dust
                r, c = nr, nc
            else:
                # 마지막 now_dust를 기억해야 함
                last_dust = now_dust
                break
    # 아래쪽 공기청정기(시계방향 순환 시작)

    # 4방향 이동
    r, c = down
    # 오른쪽 한칸 가서 시작
    c += 1
    for i in range(4):
        # 벽에 닿을 때 까지 진행
        step = 1
        if i == 0:
            # 현재 미세먼지 수를 기억
            now_dust = arr[r][c]
            arr[r][c] = 0
        else:
            now_dust = last_dust
        while True:
            nr = r + down_dr[i] * step
            nc = c + down_dc[i] * step
            if 0 <= nr < R and 0 <= nc < C and arr[nr][nc] != -1:
                # 다음 미세먼지 수를 기억
                next_dust = arr[nr][nc]
                arr[nr][nc] = now_dust
                now_dust = next_dust
                r, c = nr, nc
            else:
                # 마지막 now_dust를 기억해야 함
                last_dust = now_dust
                break

# 위쪽 공기청정기 좌표
up = ()
# 아래쪽 공기청정기 좌표
down = ()
# 공기청정기 좌표 찾기
for r in range(R):
    for c in range(C):
        if arr[r][c] == -1:
            if not up:
                up = (r, c)
            else:
                down = (r, c)

# T가 0이 되면 종료
while T > 0:
    T -= 1
    # 첫번째로 1초마다 "동시에" 진행되는 미세먼지 확산을 진행
    dust = spread(arr)
    # 주어진 튜플 배열을 순회하며 확산 진행
    for r, c, min_dust in dust:
        arr[r][c] += min_dust

    # 두번째로 공기청정기 순환을 진행함
    air_fresh(up, down)

result = 0
for r in range(R):
    for c in range(C):
        if arr[r][c] > 0:
            result += arr[r][c]
print(result)