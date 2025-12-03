N, L = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

result = 0
# 행 우선순회
for r in range(N):
    # 경사로 배열 초기화
    used = [False] * N
    for c in range(N - 1):
        # 왼쪽이 크면 left 오른쪽이 크면 right
        left = False
        right = False
        is_can = True
        # 경사로를 놓아야 하는 경우 판별
        height = arr[r][c] - arr[r][c + 1]
        if abs(height) > 1:
            break
        if height == 0:
            continue
        
        # 여기까지 내려왔으면 경사로를 놓아야 함
        # 어느쪽이 더 큰지 확인
        if arr[r][c] > arr[r][c + 1]:
            left = True
        else:
            right = True

        # 왼쪽이 더 큰 경우 오른쪽에다 경사로를 놔야함
        if left:
            c += 1
            # 현재 위치에서 L 만큼 가봄
            for i in range(L):
                # 배열의 길이를 벗어나면 불가능
                if c + i >= N:
                    is_can = False
                    break
                # 경사가 다르거나 이미 경사로를 놓은 곳이라면 불가능
                if arr[r][c] != arr[r][c + i] or used[c + i]:
                    is_can = False
                    break

        # 오른쪽이 더 큰 경우 왼쪽에서 경사로를 놓아야 함
        elif right:
            for i in range(L):
                # 배열의 경계 확인
                if c - i < 0:
                    is_can = False
                    break
                # 경사가 다르거나 이미 경사로를 놓았다면 불가능
                if arr[r][c] != arr[r][c - i] or used[c - i]:
                    is_can = False
                    break
        if not is_can:
            break

        # 경사로를 놓을 수 있다면 경사로를 놓았다고 표시함
        if left:
            for i in range(L):
                used[c + i] = True
        elif right:
            for i in range(L):
                used[c - i] = True
    else:
        result += 1


# 열 우선순회
for c in range(N):
    # 경사로 배열 초기화
    used = [False] * N
    for r in range(N - 1):
        # 왼쪽이 크면 left 오른쪽이 크면 right
        left = False
        right = False
        is_can = True
        # 경사로를 놓아야 하는 경우 판별
        height = arr[r][c] - arr[r + 1][c]
        if abs(height) > 1:
            break
        if height == 0:
            continue

        # 여기까지 내려왔으면 경사로를 놓아야 함
        # 어느쪽이 더 큰지 확인
        if arr[r][c] > arr[r + 1][c]:
            left = True
        else:
            right = True

        # 왼쪽이 더 큰 경우 오른쪽에서 경사로를 놔야함
        if left:
            r += 1
            # 현재 위치에서 L 만큼 가봄
            for i in range(L):
                # 배열의 길이를 벗어나면 불가능
                if r + i >= N:
                    is_can = False
                    break
                # 경사가 다르거나 이미 경사로를 놓은 곳이라면 불가능
                if arr[r][c] != arr[r + i][c] or used[r + i]:
                    is_can = False
                    break

        # 오른쪽이 더 큰 경우 왼쪽에서 경사로를 놓아야 함
        elif right:
            for i in range(L):
                # 배열의 경계 확인
                if r - i < 0:
                    is_can = False
                    break
                # 경사가 다르거나 이미 경사로를 놓았다면 불가능
                if arr[r][c] != arr[r - i][c] or used[r - i]:
                    is_can = False
                    break
        if not is_can:
            break

        # 경사로를 놓을 수 있다면 경사로를 놓았다고 표시함
        if left:
            for i in range(L):
                used[r + i] = True
        elif right:
            for i in range(L):
                used[r - i] = True
    else:
        result += 1

print(result)