from heapq import heappop, heappush

def bfs(arr, S, coordinate):
    coordinate2 = []
    time = 0
    # 시간이 다 되면 종료
    while True:
        if time == S:
            break
        time += 1
        # coor1로 할건지 2로 할건지 결정
        if coordinate:
            # 큐가 빌 때 까지 진행
            while coordinate:
                virus, r, c = heappop(coordinate)
                # 현재 위치에서 4방향으로 이동함
                for i in range(4):
                    nr = r + dr[i]
                    nc = c + dc[i]
                    # 배열 경계 확인
                    if 0 <= nr < N and 0 <= nc < N:
                        # 해당 칸이 0이라면 바로 전염
                        if arr[nr][nc] == 0:
                            arr[nr][nc] = virus
                            # 해당 위치를 큐에 추가함
                            heappush(coordinate2, (virus, nr, nc))
            continue

        elif coordinate2:
            # 큐가 빌 때 까지 진행
            while coordinate2:
                virus, r, c = heappop(coordinate2)
                # 현재 위치에서 4방향으로 이동함
                for i in range(4):
                    nr = r + dr[i]
                    nc = c + dc[i]
                    # 배열 경계 확인
                    if 0 <= nr < N and 0 <= nc < N:
                        # 해당 칸이 0이라면 바로 전염
                        if arr[nr][nc] == 0:
                            arr[nr][nc] = virus
                            # 해당 위치를 큐에 추가함
                            heappush(coordinate, (virus, nr, nc))
            continue


N, K = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
S, X, Y = map(int, input().split())
# 인덱스 번호 맞춰줌
X -= 1
Y -= 1

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

coordinate = []
# 배열을 순회하며 각 바이러스의 좌표를 담음
for r in range(N):
    for c in range(N):
        # 해당 위치가 바이러스라면
        if arr[r][c] != 0:
            virus = arr[r][c]
            # 해당 바이러스 번호, 좌표 형식으로 추가
            heappush(coordinate, (virus, r, c))

bfs(arr, S, coordinate)
print(arr[X][Y])