from collections import deque
N, K = map(int, input().split())
# 인덱스 맞추기
conveyor = deque(map(int, input().split()))
# 로봇 배열 생성
robot = deque([0] * N)

# 1단계부터 시작
result = 1
# 내구도 확인
durability = 0

while True:
    # 1단계 그냥 돌리는 단계
    conveyor.rotate(1)
    robot.rotate(1)
    # 내릴 수 있는지 확인
    if robot[N - 1] == 1:
        robot[N - 1] = 0

    # 2단계 로봇이 스스로 한칸 이동하는 단계
    # 로봇 배열을 거꾸로 순회하여 이동할 수 있는지 확인
    for i in range(N - 2, -1, -1):
        if robot[i] == 1 and robot[i + 1] == 0 and conveyor[i + 1] >= 1:
            conveyor[i + 1] -= 1
            robot[i] = 0
            robot[i + 1] = 1
            if conveyor[i + 1] == 0:
                durability += 1
    # 반복이 끝나고 내릴 수 있는 로봇이 있는지 확인
    if robot[N - 1] == 1:
        robot[N - 1] = 0
    # 3단계 올리는 위치에 내구도가 0인지 확인하고 로봇을 올릴 수 있다면 올리기
    if conveyor[0] >= 1:
        robot[0] = 1
        conveyor[0] -= 1
        if conveyor[0] == 0:
            durability += 1
    if durability >= K:
        break
    result += 1
print(result)