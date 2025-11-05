from itertools import combinations
from collections import deque
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def bfs(q, none_virus):
    result = 0
    # 큐가 비거나 바이러스가 모두 전파되면 종료
    while q and virus > 0:
        # 큐에서 값을 하나 꺼냄
        r, c = q.popleft()
        # 현재 위치에서 4방향 이동
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            # 경계의 배열 확인
            if 0 <= nr < N and 0 <= nc < N:
                # 만일 해당 위치가 벽이 아니고, 방문하지 않았고, 빈 칸이라면
                if arr[nr][nc] == 1:
                    continue
                elif visit[nr][nc] == -1 and arr[nr][nc] == 0:
                    # 방문 처리 후 좌표에 추가하며, 빈 칸의 수를 줄여준다.
                    visit[nr][nc] = visit[r][c] + 1
                    result = max(result, visit[nr][nc])
                    q.append((nr, nc))
                    none_virus -= 1
                # 비활성화 바이러스의 경우
                elif visit[nr][nc] == -1 and arr[nr][nc] == 2:
                    # 방문 처리 후 좌표에 "추가"만 진행
                    visit[nr][nc] = visit[r][c] + 1
                    q.append((nr, nc))
    # bfs가 끝나고 결과 집계
    if none_virus == 0:
        return result
    else:
        return -1




N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
# 입력받은 배열을 순회하며 비활성 바이러스의 위치, 개수, 바이러스가 아닌 칸의 전체 수를 파악
virus_locate = []
virus = 0
none_virus = 0
for r in range(N):
    for c in range(N):
        if arr[r][c] == 2:
            virus_locate.append((r, c))
            virus += 1
        elif arr[r][c] == 0:
            none_virus += 1

# 바이러스의 위치를 1번부터 끝까지 M개만큼 뽑는 경우의 수
virus_comb = list(combinations(virus_locate, M))



# 큐에 좌표를 순서대로 넣음
result = -1
for i in virus_comb:
    q = deque()
    # 새로운 방문 처리 배열 생성
    visit = [[-1] * N for _ in range(N)]
    for j in i:
        # 최초 방문 처리 후 큐에 삽입
        visit[j[0]][j[1]] = 0
        q.append(j)
    # 첫 좌표를 모두 넣었다면 bfs 진행
    now_result = bfs(q, none_virus)
    if now_result == -1:
        continue
    else:
        if result == -1:
            result = now_result
        else:
            result = min(result, now_result)
print(result)