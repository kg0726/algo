from collections import deque


T = int(input())
for _ in range(T):
    N, M = map(int, input().split())
    arr = list(map(int, input().split()))
    q = deque(arr)

    # 큐의 최초 최대값 초기화
    max_q = max(q)
    result = 0
    # 우선 큐가 빌 때 까지는 진행
    while q:
        # 만약 q[0] 이 최댓값이 아니라면 뒤로 넘기고 넘어감
        if q[0] != max_q:
            q.rotate(-1)
            # 타겟하고 있는 문서를 추척
            if M != 0:
                M -= 1
            else:
                M = N - 1
            continue
        # 만약 최대값이라면
        # 최대값이면서 타겟하고 있는 문서가 아닌 경우
        docs = q.popleft()
        if M > 0:
            N -= 1
            M -= 1
            # 최댓값 갱신
            max_q = max(q)
            result += 1
        # 최댓값이면서 타겟하고 있는 문서인 경우
        elif M == 0:
            result += 1
            break

    print(result)