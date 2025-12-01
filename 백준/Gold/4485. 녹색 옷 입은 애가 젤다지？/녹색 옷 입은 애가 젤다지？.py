from heapq import heappop, heappush
def bfs(start):
    inf = float('inf')
    # 빈 큐 생성
    q = []
    heappush(q, start)
    # 현재 위치 방문 처리
    visited = [[inf] * N for _ in range(N)]
    visited[0][0] = arr[0][0]
    # 큐가 빌 때 까지 진행
    while q:
        # 큐에서 값을 하나 꺼냄
        rupy, r, c = heappop(q)
        # 상하좌우 델타
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < N and 0 <= nc < N:
                if visited[nr][nc] > visited[r][c] + arr[nr][nc]:
                    visited[nr][nc] = visited[r][c] + arr[nr][nc]
                    heappush(q, (arr[nr][nc], nr, nc))

    return visited
# 상하좌우 델타
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]
K = 1
while True:
    N = int(input())
    if N == 0:
        break
    arr = [list(map(int, input().split())) for _ in range(N)]
    result = bfs((arr[0][0], 0, 0))
    print(f'Problem {K}: {result[N - 1][N - 1]}')
    K += 1