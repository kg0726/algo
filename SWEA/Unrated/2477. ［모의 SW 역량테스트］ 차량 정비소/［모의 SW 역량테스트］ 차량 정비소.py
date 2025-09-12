from collections import deque

T = int(input())
for tc in range(1, 1 + T):
    N, M, K, A, B = map(int, input().split())
    # 각 창구 별 시간이 얼마나 걸리는지의 입력
    desk_time = list(map(int, input().split()))
    fix_time = list(map(int, input().split()))
    # 손님 오는 시간
    time = list(map(int, input().split()))
    time2 = deque(time)

    # 창구 생성
    desk = [[0] for _ in range(N)]
    fix = [[0] for _ in range(M)]
    # 대기공간
    desk_waiting = deque()
    fix_waiting = deque()
    # 손님이 아무도 없으면 종료
    t = -1
    human = 0
    result = 0
    # 현재 수리 진행중인 손님 수
    in_progress = 0
    flag = True

    # 최악의 경우 시간이 40000이 넘게 증가할 수 있음
    while time2 or in_progress:
        a = 0
        b = 0
        # 시간의 흐름
        t += 1
        for i in time:
            # 손님이 도착할 시간이 되면 큐에서 한명씩 꺼내서 대기공간에 넣음
            if i == t:
                human += 1
                time2.popleft()
                desk_waiting.append(human)
                # 현재 수리중인 손님 수 추가
                in_progress += 1

        # 접수창구를 순회하며 비어있는 창구가 있고, 대기공간에 손님이 있다면 창구에 손님 정보를 넣음
        for i in range(N):
            # 접수가 종료된 고객이 있는 경우
            if desk[i] != [0] and desk[i][2] == t:
                # 고객 번호와 접수창구의 번호를 가지고 정비 대기공간으로 이동
                fix_waiting.append([desk[i][0], desk[i][1]])
                # 해당 창구를 비워줌
                desk[i] = [0]

            if desk_waiting and desk[i] == [0]:
                # 고객번호, 창구 번호와 t가 몇이면 접수가 종료될지 기록함
                desk[i] = [desk_waiting.popleft(), i, t + desk_time[i]]




        # 정비 창구를 순회하며 비어있는 창구가 있다면 해당 창구에 손님을 넣음
        for i in range(M):
            # 접수가 종료된 고객이 있는 경우 창구를 비워줌
            if fix[i] != [0] and fix[i][3] == t:
                fix[i] = [0]
                # 수리가 완료되어 손님 수 차감
                in_progress -= 1
            if fix_waiting and fix[i] == [0]:
                # 고객번호,이전 창구번호, 창구번호, t가 몇이면 접수가 종료될 지 기록함
                # 이전 창구의 정보
                prev = fix_waiting.popleft()
                fix[i] = [prev[0], prev[1], i, t + fix_time[i]]
                # 지갑을 놓고 간 손님과 동일한 창구들을 이용하였는지 체크
                if fix[i][1] == A - 1 and fix[i][2] == B - 1:
                    result += fix[i][0]


    if result == 0:
        result = -1
    print(f'#{tc} {result}')