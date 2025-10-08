N, M, D = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
from collections import deque

# 좌 상 우
dr = [0, -1, 0]
dc = [-1, 0, 1]

def find_monster(arr, r, c, catch):
    # 인자로 받은 궁수의 위치 바로 앞에 몬스터가 있는지 확인
    if arr[r - 1][c] == 1:
        if (r - 1, c) not in catch:
            return [(r - 1, c)]
    
    # 바로 앞에 몬스터가 없었다면 그 위치부터 bfs 탐색 시작
    r = r - 1
    # 몬스터 초기화
    monster = []
    # 방문 격자 생성
    visit = [[-1] * M for _ in range(N)]
    # 현재 위치 방문처리
    visit[r][c] = 0
    q = deque()
    q.append((r, c))
    # 큐가 비거나(더 갈 수 있는 곳이 없거나, 사거리까지 다 봤다면 종료)
    monsters = True
    while q and monsters:
        r, c = q.popleft()
        # 현재 위치에서 3방향 탐색
        for i in range(3):
            nr = r + dr[i]
            nc = c + dc[i]
            if 0 <= nr < N and 0 <= nc < M and visit[nr][nc] == -1:
                # 방문처리
                visit[nr][nc] = visit[r][c] + 1
                # 만약 사거리보다 먼 곳을 봤다면 종료
                if visit[nr][nc] > D - 1:
                    monsters = False
                    break
                # 사거리 내라면 큐에 삽입
                q.append((nr, nc))
                # 적을 발견했고 사거리 내라면 추가
                if arr[nr][nc] == 1 and visit[nr][nc] <= D:
                    # 만약 이미 잡은 몬스터 좌표이면 넘어감
                    if (nr, nc) in catch:
                        continue
                    monster.append((nr, nc))
                    monsters = False
                    break

    return monster


def kill_monster(archer, N):
    kill = 0
    # 몬스터가 있는 좌표 초기화
    monsters = set()
    # 잡은 몬스터가 있는 좌표 초기화
    catch = set()
    # 궁수의 조합(c 값을 인자로 받음)
    # 인자로 받은 궁수의 위치를 순회하며 해당 궁수의 바로 앞에 적이 있는지 확인(가장 가깝고 bfs를 돌려 볼 필요가 없음)
    # todo 입력으로 받은 배열이 줄어듦에 따라 N값도 같이 바뀌도록 설계
    while N > 0:
        archer_r = N
        # 궁수의 c 값을 반복을 통해 받음
        for archer_c in archer:
            for i in (find_monster(arr, archer_r, archer_c, catch)):
                monsters.add(i)

        # 모든 궁수가 사거리만큼 적을 탐색했다면 담아놓은 좌표를 순회하며 몬스터를 없앰
        for r, c in monsters:
            if arr[r][c] == 1:
                kill += 1
                # 잡은 몬스터가 있는 좌표에 추가
                catch.add((r, c))

        # 몬스터 초기화
        monsters = set()
        N -= 1

    return kill


from itertools import combinations

idx = [i for i in range(M)]
# 궁수의 자리를 생성
max_kill = 0


for i in combinations(idx, 3):
    kill = kill_monster(i, N)
    max_kill = max(kill, max_kill)
print(max_kill)