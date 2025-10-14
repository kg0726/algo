arr = [list(map(int, input().split())) for _ in range(10)]
paper = {
    1: 5,
    2: 5,
    3: 5,
    4: 5,
    5: 5,
}

def is_paper(r, c, size):
    # 해당 종이를 덮을 면적이 모두 1인지 확인하는 함수
    for i in range(size):
        for j in range(size):
            # 배열의 경계를 벗어날 수 없음
            if not (0 <= r + i < 10 and 0 <= c + j < 10):
                return False
            if arr[r + i][c + j] != 1:
                return False

    return True

def cover_paper(r, c, size):
    # 인자로 받은 사이즈 만큼 배열의 1을 0으로 바꾸는 함수
    for i in range(size):
        for j in range(size):
            arr[r + i][c + j] = 0

def back(r, c, size):
    # 덮었던 종이를 다시 걷어내며 0을 1로 바꾸는 함수
    for i in range(size):
        for j in range(size):
            arr[r + i][c + j] = 1


def recursion(count):
    global result
    # 가지치기
    if count >= result:
        return

    # 종료조건: 배열을 끝까지 다 탐색했으면 종료
    # 배열 순회
    for r in range(10):
        for c in range(10):
            # 1을 찾았다면?
            if arr[r][c] == 1:
                # 5 ~ 1 까지의 종이 크기를 거꾸로 순회하며 현재 위치에 덮을 수 있는 가장 큰 종이를 찾음
                for size in range(5, 0, -1):
                    # 해당 종이가 남아있다면 해당 종이로 덮을 수 있는지 확인
                    if paper[size] > 0 and is_paper(r, c, size):
                        # 해당 면적만큼 종이를 덮음(0으로 만듦)
                        cover_paper(r, c, size)
                        # 남은 종이 수 -1
                        paper[size] -= 1

                        # 모두 0으로 만들었다면 다음 좌표의 첫 1을 찾아서 재귀호출
                        recursion(count + 1)
                        # 백트래킹
                        back(r, c, size)
                        paper[size] += 1

                # 덮을 수 있는 모든 종이를 다 덮어봤다면 return
                # 왜 return 을 하는가?
                # 여기서 리턴하지 않는다면 위에 배열을 순회하는 for문이 계속 콜스택에 남아 성능을 저해하게 됨
                # 또한 백트래킹이 되고 나면 다시 배열에 1이 생길텐데 1을 덮지 못하는 상황이 생겨도 결과값에 반영하게 됨
                # 최종적으로 백트래킹이 완료되면 배열이 다시 입력받은 상태로 돌아가게 될 텐데 그럼 결과가 0이 되어버림
                return
    # 배열을 끝까지 다 봤다면 (if에 걸리지 않았다면 배열에 0이 없는 것)
    result = min(result, count)

result = float('inf')

# 함수 실행
recursion(0)
# 출력
if result == float('inf'):
    print(-1)
else:
    print(result)