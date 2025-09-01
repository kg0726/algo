from collections import deque
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
def maze(start_r, start_c):
    q = deque()
    # 현재 좌표 방문 체크 및 큐에 추가
    arr[start_r][start_c] = 1
    q.append([start_r, start_c])

    while q:
        r, c = q.popleft()
        # 상하좌우 4방향 이동
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < 16 and 0 <= nc < 16:
                # 통로 확인
                if arr[nr][nc] == 0:
                    # 방문 체크 및 큐에 삽입
                    arr[nr][nc] = 1
                    q.append([nr, nc])
                # 도착 확인
                elif arr[nr][nc] == 3:
                    return 1
    return 0

T = 10
for _ in range(1, 1 + T):
    tc = int(input())
    arr = [list(map(int, input().strip())) for _ in range(16)]
    # 출발점 찾기
    for i in range(16):
        for j in range(16):
            if arr[i][j] == 2:
                start_r, start_c = i, j
    print(f'#{tc}', maze(start_r, start_c))