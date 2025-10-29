# 우하좌상
dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]

def dfs(depth, arr, rotate):
    global result
    start_r = rotate[0] - rotate[2] - 1
    start_c = rotate[1] - rotate[2] - 1
    end_r = rotate[0] + rotate[2] - 1
    end_c = rotate[1] + rotate[2] - 1

    # 첫 번째 회전은 몇칸 가야 하는지 확인
    need_step = end_r - start_r
    # 90도 방향으로 몇번 돌았는지
    right = 0
    
    r = start_r
    c = start_c


    # 0칸을 가야 한다면(정사각형의 가운데면) 종료
    while need_step > 0:
        # 우하좌상 순서로 이동
        # 다음 칸에 넣기 위해 복사본 생성
        copied = arr[r][c]
        for i in range(4):
            # 가야하는 칸 수 만큼 반복
            for _ in range(need_step):
                # 다음 칸으로 이동함
                r = r + dr[i]
                c = c + dc[i]
                # 현재 칸의 값을 보존
                copied2 = arr[r][c]
                # 이동한 칸을 이전 칸의 값으로 바꿈
                arr[r][c] = copied
                # 바꾸기 이전의 현재 값
                copied = copied2

        # 해당 반복이 끝나면 need_step에서 - 2 해주고(정사각형의 안쪽으로 들어감) 우 대각선으로 현재 좌표를 이동시킴
        need_step -= 2
        r = r + 1
        c = c + 1
    # 종료조건: 깊이가 K가 되면 종료
    if depth == K - 1:
        # 행의 합을 구함
        for r in arr:
            result = min(result, sum(r))
        return


    for i in range(K):
        # 사용하지 않았다면
        if not used[i]:
            used[i] = True
            copied_arr = [j[:] for j in arr]
            dfs(depth + 1, arr, rotation[i])
            used[i] = False
            arr = [j[:] for j in copied_arr]










N, M, K = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
rotation = [list(map(int, input().split())) for _ in range(K)]
# 회전 연산을 사용했는지 여부를 체크
used = [False] * K
result = float('inf')
# 회전 연산 순회
for i in range(K):
    used[i] = True
    copied_arr = [j[:] for j in arr]
    dfs(0, arr, rotation[i])
    used[i] = False
    arr = [j[:] for j in copied_arr]


print(result)