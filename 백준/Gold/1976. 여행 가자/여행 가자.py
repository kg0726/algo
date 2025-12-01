from collections import deque
def bfs(start):
    # 빈 큐 생성
    q = deque()
    # 방문 배열 초기화
    visited = [False] * (N + 1)
    # 시작 위치를 방문처리 하고 큐에 삽입
    visited[start] = True
    q.append(start)
    # 큐가 빌 때 까지 실행
    while q:
        # 큐에서 값을 하나 꺼냄
        node = q.popleft()
        # 현재 노드와 연결되어있는 노드가 있는지 확인
        for i in arr[node]:
            # 해당 노드에 방문하지 않았다면 방문처리 후 큐에 삽입
            if not visited[i]:
                visited[i] = True
                q.append(i)
    return visited


N = int(input())
M = int(input())
# 인접 리스트 생성(0번 인덱스 사용 안함)
arr = [[] for _ in range(N + 1)]
input_arr = [list(map(int, input().split())) for _ in range(N)]

# 인접 행렬 생성
for i in range(N):
    for j in range(len(input_arr[i])):
        if input_arr[i][j] == 1:
            arr[i + 1].append(j + 1)

# 여행 계획 도시
city = list(map(int, input().split()))
set_city = set(city)
is_visited = bfs(city[0])
for i in set_city:
    if not is_visited[i]:
        print("NO")
        break
else:
    print("YES")