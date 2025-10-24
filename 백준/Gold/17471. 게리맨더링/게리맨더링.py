from collections import deque
def bfs(visit, arr):
    result = 0
    # 빈 큐 생성
    q = deque()
    # 아직 연결되지 않은 노드중 하나를 큐에 삽입
    q.append(arr[0])

    visit[arr[0]] = True
    result += 1
    while q:
        # q에서 값을 하나 꺼낸다.
        node = q.popleft()
        # 현재 노드에서 방문하지 않았고 인접한 노드들을 모두 큐에 넣는다.
        for next_node in adj_arr[node]:
            if not visit[next_node]:
                result += 1
                visit[next_node] = True
                q.append(next_node)

    # 노드 수 -1이 간선 수이면 모두 연결된 것
    return len(arr) == result

N = int(input())
# 0번 인덱스 사용 안함
human = [0] + list(map(int, input().split()))
# 인접 리스트 생성
adj_arr = [[] for _ in range(N + 1)]

for i in range(1, 1 + N):
    for j in list(map(int, input().split()))[1::]:
        adj_arr[i].append(j)

result = -1

node_arr = [i for i in range(1, 1 + N)]
sub_set = []

# 부분집합 생성
for i in range(1, 1<<len(node_arr)):
    subset = []
    for j in range(N):
        if i & (1<<j):
            subset.append(node_arr[j])
    sub_set.append(subset)

for i in sub_set:
    # 양 그룹의 visit 생성
    visit_a = [False] * (N + 1)
    visit_b = [False] * (N + 1)

    group = []
    not_link = 0
    if len(i) == N:
        continue
    for j in node_arr:
        # 부분집합 안에 있는 노드들 제외
        if j not in i:
            not_link += human[j]
            group.append(j)
            # b에 들어간다는 것은 visit_a에 포함되어 있다는 뜻
            visit_a[j] = True
        else:
            visit_b[j] = True

    if bfs(visit_a, i) and bfs(visit_b, group):
        group_a = 0
        for link in i:
            group_a += human[link]
        if result == -1:
            result = abs(group_a - not_link)
        else:
            result = min(result, abs(group_a - not_link))

print(result)