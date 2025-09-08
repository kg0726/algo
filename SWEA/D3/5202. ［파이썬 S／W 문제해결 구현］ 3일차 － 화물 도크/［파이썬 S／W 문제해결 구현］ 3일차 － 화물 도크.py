T = int(input())

def sort_key(nums):
    return nums[1] - nums[0]

for tc in range(1, 1 + T):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    # 작업 시간이 짧은 순으로 정렬
    sort_arr = sorted(arr, key=sort_key)
    # 작업중인 시간대를 담을 리스트
    working_time = []
    result = 0
    # 작업 시간들을 순회
    for i in sort_arr:
        # 작업 시작 시간부터 작업 시간이 끝날때까지 반복(작업이 끝나는 시간은 포함하지 않음)
        for j in range(i[1] - i[0]):
            work = i[0] + j
            # 작업 시간이 작업중인 시간대와 겹치면 종료
            if work in working_time:
                break
        # 겹치지 않았다면 그 시간대에 작업이 가능
        else:
            result += 1

            for j in range(i[1] - i[0]):
                work = i[0] + j
                # 작업중인 시간대에 추가
                working_time.append(work)

    print(f'#{tc} {result}')