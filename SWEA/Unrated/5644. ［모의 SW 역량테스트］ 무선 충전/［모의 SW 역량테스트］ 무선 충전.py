from collections import deque
# 10*10 배열 생성
arr = [[0] * 10 for _ in range(10)]

# 이동하지 않음, 상 우 하 좌
dr = [0, -1, 0, 1, 0]
dc = [0, 0, 1, 0, -1]

def can_charge(A_now, B_now):
    can_A = []
    can_B = []

    True_A = 0
    True_B = 0

    used_A = [False] * (CHARGE + 1)
    used_B = [False] * (CHARGE + 1)

    mix = [False] * (CHARGE + 1)
    mix_num = 0
    # A, B 각 현재 위치와 충전 가능한 충전소가 있는지 확인
    for i in range(1, CHARGE + 1):
        # A 충전 가능?
        if abs(A_now[0] - charge_info[i - 1][1]) + abs(A_now[1] - charge_info[i - 1][0]) <= charge_info[i - 1][2]:
            # 충전 출력, 충전소 번호 삽입
            can_A.append((charge_info[i - 1][3], i))
            # 충전소 사용 처리
            used_A[i] = True
            True_A += 1
        # B 충전 가능?
        if abs(B_now[0] - charge_info[i - 1][1]) + abs(B_now[1] - charge_info[i - 1][0]) <= charge_info[i - 1][2]:
            # 충전 출력, 충전소 번호 삽입
            can_B.append((charge_info[i - 1][3], i))
            used_B[i] = True
            True_B += 1

    result = 0
    # 사용 가능한 충전소가 중복되는지 확인
    for i in range(1, 1 + CHARGE):
        # 중복 확인
        if used_B[i] and used_A[i]:
            mix[i] = True
            mix_num += 1

    # 겹치는 충전소가 있는 경우로 분기
    if mix_num >= 1:
        # 겹치는 충전소가 단 한개이고, 둘 다 갈 수 있는 충전소가 하나뿐인 경우
        if True_A == 1 and True_A == True_B and mix_num == 1:
            # 해당 위치에서 충전
            result += can_A[0][0]

        # 겹치는 충전소는 하나이지만 꼭 같이 충전할 필요는 없는 경우
        elif mix_num == 1 and True_A == 1 and True_B > 1:
            # B를 내림차순으로 sort
            can_B.sort(reverse=True)
            result += can_A[0][0]
            for i in range(len(can_B)):
                if can_A[0][1] != can_B[i][1]:
                    result += can_B[i][0]
                    break

        elif mix_num == 1 and True_B == 1 and True_A > 1:
            # A를 내림차순으로 sort
            can_A.sort(reverse=True)
            result += can_B[0][0]
            for i in range(len(can_A)):
                if can_B[0][1] != can_A[i][1]:
                    result += can_A[i][0]
                    break

        # 걍 높은거 두개 선택하면 되는 경우
        else:
            tmp1 = 0
            tmp2 = 0
            can_A.sort(reverse=True)
            can_B.sort(reverse=True)
            tmp1 += can_A[0][0]
            for i in range(True_B):
                # A가 선택한 위치와 겹치는지 확인
                if can_B[i][1] != can_A[0][1]:
                    tmp1 += can_B[i][0]
                    break

            tmp2 += can_B[0][0]
            for i in range(True_A):
                if can_A[i][1] != can_B[0][1]:
                    tmp2 += can_A[i][0]
                    break
            # 둘 중 큰거로 result에 추가
            if tmp1 >= tmp2:
                result += tmp1
            elif tmp1 < tmp2:
                result += tmp2

    # 겹치지 않는 경우
    else:
        can_A.sort(reverse=True)
        can_B.sort(reverse=True)
        if can_A:
            result += can_A[0][0]
        if can_B:
            result += can_B[0][0]

    return result
                





T = int(input())
for tc in range(1, 1 + T):
    M, CHARGE = map(int, input().split())
    A = deque(map(int, input().split()))
    B = deque(map(int, input().split()))
    charge_info = [(list(map(int, input().split()))) for _ in range(CHARGE)]
    # 인덱스 맞춰주기
    for i in range(len(charge_info)):
        charge_info[i][0] -= 1
        charge_info[i][1] -= 1

    # a와 b의 시작 좌표 초기화
    A_now = [0, 0]
    B_now = [9, 9]
    # 결과 초기화
    result = can_charge(A_now, B_now)
    # A와 B가 비지 않는동안 실행
    while A and B:
        # 큐에서 각자 값을 뽑음
        A_dir = A.popleft()
        B_dir = B.popleft()
        A_now[0] = A_now[0] + dr[A_dir]
        A_now[1] = A_now[1] + dc[A_dir]

        B_now[0] = B_now[0] + dr[B_dir]
        B_now[1] = B_now[1] + dc[B_dir]
        result += can_charge(A_now, B_now)
    print(f'#{tc} {result}')