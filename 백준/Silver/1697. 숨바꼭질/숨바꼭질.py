N, K = map(int, input().split())
from collections import deque 
# 방문 배열 초기화
visited = [0] * 100001
# 큐를 초기화하고 시작 위치를 큐에 삽입함
# 큐 삽입 형식: (위치, 해당 위치까지 오기 위해 소요한 시간)
q = deque()
q.append((N, 0))

result = float('inf')

# 큐가 빌 때 까지 진행
while q:
    # 큐에서 값을 하나 꺼냄
    node, time = q.popleft()
    # 만약 현재 노드가 도착지라면 최솟값 갱신을 진행
    if node == K:
        result = min(result, visited[node])
        continue
    # 만약 현재 time이 result보다 크거나 같다면 더 이상 볼 필요 없음
    if time >= result:
        continue
    next_time = time + 1
    node_plus = node + 1
    node_minus = node - 1
    node_multiplication = node * 2
    # 현재 노드에서 갈 수 있는 모든 노드를 큐에 삽입
    if 0 <= node_plus <= 100000 and node_plus != N:
        if visited[node_plus] == 0:
            visited[node_plus] = next_time
            q.append((node_plus, next_time))
        elif visited[node_plus] >= next_time:
            visited[node_plus] = next_time
            q.append((node_plus, next_time))

    if 0 <= node_minus <= 100000 and node_minus != N:
        if visited[node_minus] == 0:
            visited[node_minus] = next_time
            q.append((node_minus, next_time))
        elif visited[node_minus] >= next_time:
            visited[node_minus] = next_time
            q.append((node_minus, next_time))

    if 0 < node_multiplication <= 100000 and node_multiplication != N:
        if visited[node_multiplication] == 0:
            visited[node_multiplication] = next_time
            q.append((node_multiplication, next_time))
        elif visited[node_multiplication] >= next_time:
            visited[node_multiplication] = next_time
            q.append((node_multiplication, next_time))

print(result)