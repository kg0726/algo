def find_set(parents, x):
    # 인자로 받은 x의 부모를 찾는 함수
    # 본인이 부모라면 종료
    if parents[x] == x:
        return x
    # 본인이 부모일 때 까지 재귀 호출
    root = find_set(parents, parents[x])
    # 리턴 안해주면 재귀가 호출된 후 종료되는 시점에 마지막으로 함수가 종료되므로 None이 반환됨
    return root

def union(parents, x, y):
    # 서로 다른 집합을 하나의 집합으로 합치는 함수
    root_x = find_set(parents, x)
    root_y = find_set(parents, y)
    # 만약 부모가 같다면 이미 합쳐져 있음
    if root_y == root_x:
        return

    # 정점 번호가 더 작은 쪽을 부모로 합쳐줌
    if root_y > root_x:
        parents[root_y] = root_x
    else:
        parents[root_x] = root_y

def mst_by_kruskal(arr, V):
    # 인자로 받은 인접 리스트를 최소신장트리로 구성하는 함수
    # 사이클을 판별할 때 사용할 부모 리스트 생성
    parents = [i for i in range(V + 1)]
    # 인자로 받은 리스트를 가중치가 작은 순서대로 정렬
    arr.sort()
    # 최소신장트리의 가중치의 합 초기화
    mst = 0
    # 최소신장트리의 간선 개수 초기화
    mst_edge = 0
    # 정렬된 리스트 순회
    for weight, start, end in arr:
        # 사이클 여부 확인(부모가 같은지)
        if find_set(parents, start) == find_set(parents, end):
            continue
        # 사이클을 기록하여 중복 방지
        union(parents, start, end)
        mst += weight
        mst_edge += 1

        # 현재 생성된 간선이 정점의 개수 -1 이면 모든 정점이 연결됨
        if mst_edge == V:   # 0부터 시작이므로 마지막 노드번호가 곧 노드의 수 -1이 됨
            return mst



T = int(input())
for tc in range(1, 1 + T):
    V, E = map(int, input().split())
    arr = []
    for _ in range(E):
        n1, n2, w = map(int, input().split())
        arr.append((w, n1, n2))       # 가중치, 시작노드, 도착노드
        arr.append((w, n2, n1))

    # 함수 실행 및 출력
    result = mst_by_kruskal(arr, V)
    print(f'#{tc} {result}')