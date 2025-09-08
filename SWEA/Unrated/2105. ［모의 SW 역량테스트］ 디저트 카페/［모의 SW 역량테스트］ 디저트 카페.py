# 우상, 우하, 좌하, 좌상(하나의 최대값만 찾으면 되므로 시계방향으로만 회전하는 경우만 봄)
# 마지막 5번째는 인덱스 오류를 막기 위함(한바퀴 돌고 또 도는 경우)
dr = [-1, 1, 1, -1, -1]
dc = [1, 1, -1, -1, 1]
def find_cafe(r, c, visit, direction, start):
    global result
    '''
    :param r: 행 
    :param c: 열
    :param visit: 먹은 디저트를 저장 
    :param direction: 이동시 방향을 제어할 변수
    '''
    # 종료조건 3번 이상 회전 했을 경우(사각형을 그릴 수 없음)
    if direction > 3:
        return

    # 종료조건 3번 회전했고, 현재 위치가 출발 위치인 경우
    if direction == 3 and [r, c] == start:
        # 최대값 판별 로직
        result = max(result, len(visit))
        return result



    # 재귀 호출
    # i == direction: 방향을 바꾸지 않고 직진하는 경우
    # i == direction + 1: 방향을 바꾼 경우
    for i in [direction, direction + 1]:
        nr = r + dr[i]
        nc = c + dc[i]
        # 배열이 유효한 경우
        if 0 <= nr < N and 0 <= nc < N:
            # 이미 먹은 디저트가 아닐 경우
            if arr[nr][nc] not in visit:
                # 재귀 호출
                # 현재 위치의 디저트 먹었다고 표시
                visit.append(arr[nr][nc])
                find_cafe(nr, nc, visit, i, start)
                # 백트래킹
                visit.pop()



T = int(input())
for tc in range(1, 1 + T):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    # 이미 먹은 디저트를 확인 할 리스트
    visit = []
    # 최대값
    result = -1
    for r in range(N):
        for c in range(N):
            find_cafe(r, c, visit, 0, [r, c])

    print(f'#{tc}', result)