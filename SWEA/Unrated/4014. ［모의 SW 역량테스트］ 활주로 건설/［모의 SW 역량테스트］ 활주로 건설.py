T = int(input())
for tc in range(1, 1 + T):
    N, X = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]
    # 결과 초기화
    result = 0

    # 1. 열 우선순회
    for r in range(N):
        flag = True
        #         # 경사로로 사용된 칸을 표시하기 위한 방문 배열
        visit = [False] * N
        # 현재 칸, 그 다음 칸 비교
        for c in range(N - 1):
            # 현재 칸과 다음 칸을 비교
            # 높이 차이가 2 이상이면 불가능
            if abs(arr[r][c] - arr[r][c + 1]) >= 2:
                break

            # 왼쪽이 더 큰 경우 오른쪽에 경사로를 설치해야 함
            elif arr[r][c] - arr[r][c + 1] == 1:
                # 다음 위치 포함 X 칸 만큼 다음 조건을 만족하는지 확인
                # 조건 1. 배열의 경계
                # 조건 2. 높이가 같아야 함
                # 조건 3. 경사로로 사용된 적이 없어야 함
                for i in range(X):
                    if c + 1 + i < N and arr[r][c + 1] == arr[r][c + 1 + i] and not visit[c + 1 + i]:
                        # 방문 처리
                        visit[c + 1 + i] = True
                    # 조건을 만족하지 않는다면 해당 행은 불가능
                    else:
                        flag = False
                        break
            # 오른쪽이 더 큰 경우 왼쪽에 경사로를 설치해야 함
            elif arr[r][c] - arr[r][c + 1] == -1:
                # 조건 3개 만족하는지
                for i in range(X):
                    if 0 <= c - i and arr[r][c] == arr[r][c - i] and not visit[c - i]:
                        # 방문 처리
                        visit[c - i] = True
                    else:
                        flag = False
                        break
            if not flag:
                break
        else:
            result += 1
    # 2. 행 우선순회
    for c in range(N):
        flag = True
        #         # 경사로로 사용된 칸을 표시하기 위한 방문 배열
        visit = [False] * N
        # 현재 칸, 그 다음 칸 비교
        for r in range(N - 1):
            # 현재 칸과 다음 칸을 비교
            # 높이 차이가 2 이상이면 불가능
            if abs(arr[r][c] - arr[r + 1][c]) >= 2:
                break

            # 왼쪽이 더 큰 경우 오른쪽에 경사로를 설치해야 함
            elif arr[r][c] - arr[r + 1][c] == 1:
                # 다음 위치 포함 X 칸 만큼 다음 조건을 만족하는지 확인
                # 조건 1. 배열의 경계
                # 조건 2. 높이가 같아야 함
                # 조건 3. 경사로로 사용된 적이 없어야 함
                for i in range(X):
                    if r + 1 + i < N and arr[r + 1][c] == arr[r + 1 + i][c] and not visit[r + 1 + i]:
                        # 방문 처리
                        visit[r + 1 + i] = True
                    # 조건을 만족하지 않는다면 해당 행은 불가능
                    else:
                        flag = False
                        break
            # 오른쪽이 더 큰 경우 왼쪽에 경사로를 설치해야 함
            elif arr[r][c] - arr[r + 1][c] == -1:
                # 조건 3개 만족하는지
                for i in range(X):
                    if 0 <= r - i and arr[r][c] == arr[r - i][c] and not visit[r - i]:
                        # 방문 처리
                        visit[r - i] = True
                    else:
                        flag = False
                        break
            if not flag:
                break
        else:
            result += 1


    print(f'#{tc} {result}')