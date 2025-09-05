def sum_num(path, nums):
    # 숫자 배열과 만들어진 연산자 배열을 인자로 받아 연산 결과를 반환합니다.
    result = 0
    for i in range(len(path)):
        if path[i] == '+':
            if i == 0:
                result = nums[i] + nums[i + 1]
            else:
                result = result + nums[i + 1]

        elif path[i] == '-':
            if i == 0:
                result = nums[i] - nums[i + 1]
            else:
                result = result - nums[i + 1]

        elif path[i] == '*':
            if i == 0:
                result = nums[i] * nums[i + 1]
            else:
                result = result * nums[i + 1]

        elif path[i] == '/':
            if i == 0:
                result = nums[i] / nums[i + 1]
                result = int(result)
            else:
                result = result / nums[i + 1]
                result = int(result)

    return result

def make_nums(n, plus, minus, mul, div):
    global max_num, min_num
    # 종료조건
    if n == sum(arr):
        num = sum_num(path, nums)
        max_num = max(max_num, num)
        min_num = min(min_num, num)
        return

    # 연산자 배열 순회
    for i in range(len(arr)):
        # 더하기
        if i == 0 and plus > 0:
            path.append('+')
            make_nums(n + 1, plus - 1, minus, mul, div)
            # 백트래킹
            path.pop()
        # 빼기
        elif i == 1 and minus > 0:
            path.append('-')
            make_nums(n + 1, plus, minus - 1, mul, div)
            # 백트래킹
            path.pop()
        # 곱하기
        elif i == 2 and mul > 0:
            path.append('*')
            make_nums(n + 1, plus, minus, mul - 1, div)
            # 백트래킹
            path.pop()
        # 나누기
        elif i == 3 and div > 0:
            path.append('/')
            make_nums(n + 1, plus, minus, mul, div - 1)
            # 백트래킹
            path.pop()



T = int(input())
for tc in range(1, 1 + T):
    N = int(input())
    #    + - * /
    arr = list(map(int, input().split()))
    nums = list(map(int, input().split()))

    path = []
    max_num = -1000000
    min_num = float('inf')

    make_nums(0, arr[0], arr[1], arr[2], arr[3])
    print(f'#{tc}', max_num - min_num)