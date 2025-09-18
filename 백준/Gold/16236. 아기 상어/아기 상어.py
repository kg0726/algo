from collections import deque
# 상하좌우
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def eat_fish(start_r, start_c, fish_cnt, arr):
    # 빈 큐 생성
    q = deque()
    # 방문 기록표 초기화
    visit = [[False] * N for _ in range(N)]
    # 현재 위치에서 시작 처리
    visit[start_r][start_c] = True
    # 큐에 삽입(거리 누적합, 좌표)
    q.append((0, start_r, start_c))
    # 일단 트루로 돌리고 더 이상 먹을 수 있는 물고기가 없을 때 까지
    # 상어 크기 초기화
    shark = 2
    # 먹은 물고기 수 초기화
    fish = 0
    # 시간 초기화
    result = 0
    # 잡아먹힐 물고기 후보 리스트
    bench = []
    while True:
        # 큐가 비었다면 어느 물고기를 먹을 것인지
        if not q:
            # 물고기가 한마리면 그것만 먹음
            if len(bench) == 0:
                break
            bench.sort()
            min_time = bench[0][0]
            min_r = bench[0][1]
            min_c = bench[0][2]
            for i in bench[1::]:
                if i[0] > min_time:
                    break
                    # 좌표 최솟값 갱신
                if min_r > i[1]:
                    min_r = i[1]
                elif min_r == i[1]:
                    if min_c > i[2]:
                        min_c = i[2]
            # 반복이 종료되었다면 어느 물고기를 먹을 것인지 선택이 완료 되었을 것임
            time, r, c = 0, min_r, min_c
            result += min_time
            fish += 1
            # 상어 크기 처리
            if shark == fish:
                shark += 1
                fish = 0
            # 방문 기록 초기화
            visit = [[False] * N for _ in range(N)]
            # 후보 초기화
            bench = []
            # 먹은 곳 초기화
            arr[r][c] = 0

        if q:    # 큐가 비어있지 않다면
            # 큐에서 꺼내어 각각 할당
            time, r, c = q.popleft()

        # 4방향 탐색
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            # 지나갈 수 있는 길인지 확인
            if 0 <= nr < N and 0 <= nc < N and not visit[nr][nc] and arr[nr][nc] <= shark:
                # 방문처리
                visit[nr][nc] = True
                # 지금 가려는 위치가 먹을 수 있는 물고기가 있는 위치라면, 걸리는 시간과 좌표를 후보군에 추가한다.
                if 0 < arr[nr][nc] < shark:
                    bench.append((time + 1, nr, nc))
                    q.append((time + 1, nr, nc))
                else:
                    q.append((time + 1, nr, nc))

    return result


# import sys
# sys.stdin = open('shark.txt')

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
# 상어 위치 찾기
where = 0
# 물고기 마릿수 초기화
fish_cnt = 0
for r in range(N):
    for c in range(N):
        if arr[r][c] == 9:
            # 상어의 위치 추출
            where = [r, c]
            # 탐색의 편의를 위해 최초 상어의 위치도 0으로 고정
            arr[r][c] = 0
            # 물고기의 마리수 탐색
        if 1 <= arr[r][c] <= 6:
            fish_cnt += 1

print(eat_fish(where[0], where[1], fish_cnt, arr))