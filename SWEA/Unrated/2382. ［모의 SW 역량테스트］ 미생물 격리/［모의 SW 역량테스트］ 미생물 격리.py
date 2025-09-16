# 상하좌우 델타 (0번 인덱스 사용 안함)
dr = [0, -1, 1, 0, 0]
dc = [0, 0, 0, -1, 1]
# 방향 바꿔줄때 사용할 변수
switch = [0, 2, 1, 4, 3]

T = int(input())
for tc in range(1, 1 + T):
    N, M, K = map(int, input().split())
    # 미생물 군집 별로 번호 부여
    arr = [[i] + list(map(int, input().split())) for i in range(K)]
    # 격리 시간이 끝날 때 까지 반복
    while M > 0:
        # 미생물 이동 시작
        M -= 1
        # 미생물의 이동 정보를 담을 기록표 초기화
        visit = [[False] * N for _ in range(N)]
        # 겹친게 있는지 없는지 확인할 변수 초기화
        duplication = False
        # 이동한 미생물 군집의 최종 정보를 담을 리스트 초기화
        new_arr = [[] for _ in range(K)]
        # 이동 시작
        for i in arr:
            if i:
                num, r, c, many, dir = i
            else:
                continue

            nr = r + dr[dir]
            nc = c + dc[dir]

            # 만약 약품이 칠해진 구역에 닿았다면
            if nr == 0 or nr == N - 1 or nc == 0 or nc == N - 1:
                # 미생물 수를 반으로 줄임
                many = int(many / 2)
                dir = switch[dir]
                # 미생물 수가 0이 되었다면 업데이트 하지 않음(군집 삭제)
                if many == 0:
                    continue

            # 이동 후 visit에 값을 추가
            # 해당 위치에 아무것도 없다면
            if not visit[nr][nc]:
                visit[nr][nc] = [(num, nr, nc, many, dir)]   # 미생물 번호, 좌표, 좌표, 미생물 수, 방향
                # 새로운 미생물 군집의 정보로 업데이트
                new_arr[num] = (num, nr, nc, many, dir)
            else:
                duplication = True
                visit[nr][nc].append((num, nr, nc, many, dir))

        # 이동 종료 후 겹치는 미생물 군집이 있었는지 확인
        if not duplication:
            # 미생물 정보 업데이트
            arr = new_arr
            continue
        # 겹치는 군집이 있었다면
        for r in range(N):
            for c in range(N):
                if visit[r][c] and len(visit[r][c]) > 1:
                    # 가장 많은 미생물 수 초기화
                    max_num = 0
                    # 겹친 군집의 미생물 합
                    sum_many = 0
                    # 첫번째로 들어간 미생물 번호
                    first_num = K + 10
                    # 해당 칸의 좌표
                    dup_r = r
                    dup_c = c
                    # 해당 칸의 방향
                    direction = 0
                    # 해당 칸의 미생물 정보를 순회
                    for num, i, j, many, dir in visit[r][c]:
                        sum_many += many
                        # 만약 가장 많았던 미생물 군집보다 더 많은 미생물 군집이 나타나면
                        if max_num < many:
                            # 최댓값, 방향, 번호 갱신
                            max_num = many
                            direction = dir
                        # 첫번째로 들어간 미생물 번호
                        if first_num > num:
                            first_num = num
                    # 반복이 끝나면 새로운 미생물 군집 정보로 업데이트
                    new_arr[first_num] = (first_num, dup_r, dup_c, sum_many, direction)

        # 미생물 정보 업데이트
        arr = new_arr

    # 결과값 뽑기
    result = 0
    for i in arr:
        if i:
            num, r, c, many, dir = i
            result += many

    print(f'#{tc} {result}')