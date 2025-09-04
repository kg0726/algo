import sys
sys.stdin = open('mag.txt')
from collections import deque
T = int(input())
for tc in range(1, 1 + T):
    K = int(input())
    arr = [[]] + [list(map(int, input().split())) for _ in range(4)]
    twist = [list(map(int, input().split())) for _ in range(K)]
    # 각 톱니가 몇번 회전했는지를 담을 리스트 0번 인덱스 사용 안함
    twist_num = [0] * 5

    # 각 자석 정보를 큐에 담음
    mag1 = deque(arr[1])
    mag2 = deque(arr[2])
    mag3 = deque(arr[3])
    mag4 = deque(arr[4])

    for i in twist:
        # 회전 여부를 결정(0: 회전하지 않음, 1: 시계, -1: 반시계)
        direction = [0] * 5

        # 첫번째 자석
        if i[0] == 1:
            # 시계방향
            if i[1] == 1:
                direction[1] = 1
                # 1, 2가 다를 경우
                if mag1[2] != mag2[-2]:
                    direction[2] = -1
                    # 2와 3이 다를 경우
                    if mag2[2] != mag3[-2]:
                        direction[3] = 1
                        # 3과 4가 다를 경우
                        if mag3[2] != mag4[-2]:
                            direction[4] = -1
            else:
                direction[1] = -1
                # 1, 2가 다를 경우
                if mag1[2] != mag2[-2]:
                    direction[2] = 1
                    # 2와 3이 다를 경우
                    if mag2[2] != mag3[-2]:
                        direction[3] = -1
                        # 3과 4가 다를 경우
                        if mag3[2] != mag4[-2]:
                            direction[4] = 1
             # 두번째 자석
        elif i[0] == 2:
            # 시계방향
            if i[1] == 1:
                direction[2] = 1
                # 2, 1이 다를 경우
                if mag2[-2] != mag1[2]:
                    direction[1] = -1
                # 2와 3이 다를 경우
                if mag2[2] != mag3[-2]:
                    direction[3] = -1
                    # 3과 4가 다를 경우
                    if mag3[2] != mag4[-2]:
                        direction[4] = 1
            # 반시계
            else:
                direction[2] = -1
                # 2, 1이 다를 경우
                if mag2[-2] != mag1[2]:
                    direction[1] = 1
                # 2와 3이 다를 경우
                if mag2[2] != mag3[-2]:
                    direction[3] = 1
                    # 3과 4가 다를 경우
                    if mag3[2] != mag4[-2]:
                        direction[4] = -1
        # 세번째 자석
        elif i[0] == 3:
            # 시계방향
            if i[1] == 1:
                direction[3] = 1
                # 3과 2가 다를 경우
                if mag3[-2] != mag2[2]:
                    direction[2] = -1
                    # 2와 1이 다를 경우
                    if mag2[-2] != mag1[2]:
                        direction[1] = 1
                # 3과 4가 다를 경우
                if mag3[2] != mag4[-2]:
                    direction[4] = -1
            # 반시계
            else:
                direction[3] = -1
                # 3과 2가 다를 경우
                if mag3[-2] != mag2[2]:
                    direction[2] = 1
                    # 2와 1이 다를 경우
                    if mag2[-2] != mag1[2]:
                        direction[1] = -1
                # 3과 4가 다를 경우
                if mag3[2] != mag4[-2]:
                    direction[4] = 1

        # 네번째 자석
        else:
            if i[1] == 1:
                direction[4] = 1
                # 4와 3이 다를 경우
                if mag4[-2] != mag3[2]:
                    direction[3] = -1
                    # 3 2
                    if mag3[-2] != mag2[2]:
                        direction[2] = 1
                        # 2 1
                        if mag2[-2] != mag1[2]:
                            direction[1] = -1
            # 반시계
            else:
                direction[4] = -1
                # 4와 3이 다를 경우
                if mag4[-2] != mag3[2]:
                    direction[3] = 1
                    # 3 2
                    if mag3[-2] != mag2[2]:
                        direction[2] = -1
                        # 2 1
                        if mag2[-2] != mag1[2]:
                            direction[1] = 1

        # 조건문을 통과하면 각 톱니바퀴가 어느 방향으로 돌아야 할 지 결정되어 있을 것.
        for j in range(1, len(direction)):
            if j == 1 and direction[j] != 0:
                # 회전
                mag1.rotate(direction[j])
                # 점수
                twist_num[j] += direction[j]
            elif j == 2 and direction[j] != 0:
                mag2.rotate(direction[j])
                twist_num[j] += direction[j]
            elif j == 3 and direction[j] != 0:
                mag3.rotate(direction[j])
                twist_num[j] += direction[j]
            elif j == 4 and direction[j] != 0:
                mag4.rotate(direction[j])
                twist_num[j] += direction[j]

    result = 0
    if mag1[0] == 1:
        result += 1
    if mag2[0] == 1:
        result += 2
    if mag3[0] == 1:
        result += 4
    if mag4[0] == 1:
        result += 8

    print(f'#{tc}', result)








