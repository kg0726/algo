from collections import deque

def move_left(arr):
    # 0이 아닌 숫자들을 모음
    none_zero = [[] for _ in range(N)]
    # 새로운 배열을 생성
    new_arr = [[0] * N for _ in range(N)]
    # 왼쪽으로 이동시키는 함수
    # 왼쪽에서 오른쪽(정방향으로 열 우선순회)
    for r in range(N):
        no_zero = []
        for c in range(N):
            # 0이 아닌 값들만 모음
            if arr[r][c] != 0:
                no_zero.append(arr[r][c])
        none_zero[r] = no_zero

    # 0이 아닌 집합들을 순회함
    r = -1
    for i in none_zero:
        r += 1
        c = 0
        idx = 0
        while idx < len(i):
            # 현재 숫자와 다음 숫자가 같고, 인덱스가 배열 범위를 안 넘을 때
            if idx + 1 < len(i) and i[idx] == i[idx + 1]:
                new_arr[r][c] = i[idx] + i[idx + 1]
                c += 1
                idx += 2  # 병합된 경우 → 두 칸 건너뜀
            else:
                new_arr[r][c] = i[idx]
                c += 1
                idx += 1  # 병합되지 않은 경우 → 한 칸만 이동

    return new_arr


def move_right(arr):
    # 오른쪽으로 이동시키는 함수
    # 오른쪽에서 왼쪽(역방향으로 열 우선순회)
    # 이동시킬 때 사용
    move = deque()
    for r in range(N - 1, -1, -1):
        for c in range(N - 1, 0, -1):
            # 오른쪽, 왼쪽 값이 다를 때(현재 좌표가 합쳐지지 않을 때)
            if arr[r][c] != arr[r][c - 1]:
                if arr[r][c] != 0:
                    move.append(arr[r][c])
                    arr[r][c] = 0
            # 마지막 값 고려
            if c == 1 and arr[r][c - 1] != 0:
                move.append(arr[r][c - 1])
                arr[r][c - 1] = 0

            else:
                if arr[r][c] != 0:
                    move.append(arr[r][c] + arr[r][c - 1])
                    arr[r][c], arr[r][c - 1] = 0, 0
        # move에 담은 모든 원소들을 선입선출 방식으로 원본에 삽입
        i = N - 1
        while move:
            num = move.popleft()
            arr[r][i] = num
            i -= 1

def move_right(arr):
    none_zero = [[] for _ in range(N)]
    new_arr = [[0] * N for _ in range(N)]
    # 오른쪽으로 이동시키는 함수
    for r in range(N):
        no_zero = []
        # 오른쪽부터 왼쪽으로 확인
        for c in range(N - 1, -1, -1):
            if arr[r][c] != 0:
                no_zero.append(arr[r][c])
        none_zero[r] = no_zero

    r = -1
    for i in none_zero:
        r += 1
        c = N - 1
        idx = 0
        while idx < len(i):
            if idx + 1 < len(i) and i[idx] == i[idx + 1]:
                new_arr[r][c] = i[idx] + i[idx + 1]
                c -= 1
                idx += 2
            else:
                new_arr[r][c] = i[idx]
                c -= 1
                idx += 1
    return new_arr


def move_up(arr):
    none_zero = [[] for _ in range(N)]
    new_arr = [[0] * N for _ in range(N)]
    # 위쪽으로 이동시키는 함수
    for c in range(N):
        no_zero = []
        for r in range(N):
            if arr[r][c] != 0:
                no_zero.append(arr[r][c])
        none_zero[c] = no_zero

    c = -1
    for i in none_zero:
        c += 1
        r = 0
        idx = 0
        while idx < len(i):
            if idx + 1 < len(i) and i[idx] == i[idx + 1]:
                new_arr[r][c] = i[idx] + i[idx + 1]
                r += 1
                idx += 2
            else:
                new_arr[r][c] = i[idx]
                r += 1
                idx += 1
    return new_arr


def move_down(arr):
    none_zero = [[] for _ in range(N)]
    new_arr = [[0] * N for _ in range(N)]
    # 아래쪽으로 이동시키는 함수
    for c in range(N):
        no_zero = []
        for r in range(N - 1, -1, -1):
            if arr[r][c] != 0:
                no_zero.append(arr[r][c])
        none_zero[c] = no_zero

    c = -1
    for i in none_zero:
        c += 1
        r = N - 1
        idx = 0
        while idx < len(i):
            if idx + 1 < len(i) and i[idx] == i[idx + 1]:
                new_arr[r][c] = i[idx] + i[idx + 1]
                r -= 1
                idx += 2
            else:
                new_arr[r][c] = i[idx]
                r -= 1
                idx += 1

    return new_arr

def move_nums(chance, arr):
    global result
    # 종료조건: 문제에서 주어진 시행횟수(5번)이 끝나면 종료
    if chance <= 0:
        arr_max = list(map(max, arr))
        result = max(result, max(arr_max))
        return

    # 백트레킹을 위해 현재 배열 복사
    copied_arr = [i[:] for i in arr]

    # 상하좌우 4방향을 모두 봐야함
    for i in range(4):
        if i == 0:
            new_arr = move_left(arr)
        elif i == 1:
            new_arr = move_right(arr)
        elif i == 2:
            new_arr = move_up(arr)
        else:
            new_arr = move_down(arr)

        # 다음 회차로 재귀호출
        move_nums(chance - 1, new_arr)

        # 백트래킹
        arr = [i[:] for i in copied_arr]

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]
result = 0

move_nums(5, arr)
print(result)