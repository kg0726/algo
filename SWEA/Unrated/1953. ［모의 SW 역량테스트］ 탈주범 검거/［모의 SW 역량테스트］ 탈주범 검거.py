from collections import deque
# 델타 상하좌우
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
# 터널별로 갈 수 있는 곳
types = {
    1: [1, 1, 1, 1],
    2: [1, 1, 0, 0],
    3: [0, 0, 1, 1],
    4: [1, 0, 0, 1],
    5: [0, 1, 0, 1],
    6: [0, 1, 1, 0],
    7: [1, 0, 1, 0],
}
# 길이 연결되었는지 확인할 리스트(상하좌우)
way = [1, 0, 3, 2]

def bfs(R, C):
    q = deque()
    # 현재 위치 1로 초기화
    visit[R][C] = 1
    # 해당 위치를 큐에 넣음
    q.append([R, C])
    # 큐가 비면 더 이상 갈 곳이 없다는 뜻
    # 큐가 빌 때 까지 진행
    while q:
        r, c = q.popleft()
        # 현재 위치에서 갈 수 있는 방향을 확인
        possible_direction = types[arr[r][c]]
        # 현재 위치에서 4방향 이동
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            # 배열의 경계 확인
            if 0 <= nr < N and 0 <= nc < M and visit[nr][nc] == 0:
                # 해당 위치가 길이 아니면 넘어감
                if arr[nr][nc] == 0:
                    continue
                # 다음 터널이 열려있는 방향 확인
                next_direction = types[arr[nr][nc]]
                # 현재 위치에서 갈 수 있는 곳인지 확인
                if possible_direction[i] == 0:
                    continue
                # 다음 위치로 가려고 하는데 터널이 열려 있지 않으면 넘어감
                if next_direction[way[i]] == 0:
                    continue
                # 다음 위치에 방문표시 후 큐에 삽입
                visit[nr][nc] = visit[r][c] + 1
                q.append([nr, nc])

T = int(input())
for tc in range(1, 1 + T):
    N, M, R, C, L = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]
    # 주어진 배열과 동일한 방문 기록표 생성
    visit = [[0] * M for _ in range(N)]
    bfs(R, C)
    result = 0

    # 방문 기록된 배열을 순회하며 0부터 L인 좌표의 수를 카운팅
    for r in range(N):
        for c in range(M):
            if 1 <= visit[r][c] and visit[r][c] <= L:
                result += 1
    print(f'#{tc} {result}')