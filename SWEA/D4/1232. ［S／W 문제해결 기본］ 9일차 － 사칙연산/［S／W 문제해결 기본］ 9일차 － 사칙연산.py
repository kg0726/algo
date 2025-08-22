def calculate_inorder(node):
    '''
    노드 번호를 인자로 받아 중위순회하며,
    순회 중 연산자를 만나면 해당 연산자의
    왼쪽 자식과 오른쪽 자식의 연산 결과를리턴하는 함수
    '''
    # base case arr[node]의 길이가 2면 더 이상 자식이 없음
    if len(arr[int(node)]) == 2:
        # 숫자를 담음
        return arr[int(node)][1]

    # arr의 길이가 4면 왼쪽, 오른쪽 자식 모두 존재
    elif len(arr[int(node)]) == 4:
        # 왼쪽 자식 확인
        num1 = calculate_inorder(arr[int(node)][2])
        # 본인(연산자)
        operator = arr[int(node)][1]
        # 오른쪽 자식 확인
        num2 = calculate_inorder(arr[int(node)][3])
        # 연산 진행
        if operator == '-':
            return int(num1) - int(num2)
        elif operator == '*':
            return int(num1) * int(num2)
        elif operator == '+':
            return int(num1) + int(num2)
        else:
            return int(int(num1) / int(num2))

T = 10
for tc in range(1, 1 + T):
    N = int(input())
    # 입력으로 들어오는 노드 정보를 담을 빈 배열 생성
    # 0번 인덱스는 사용하지 않을 것이므로 1개 더 생성
    arr = [[] for _ in range(N + 1)]
    # 입력으로 들어오는 노드 정보의 각 줄을 빈 배열에 담음
    for _ in range(N):
        # 한줄씩 들어오는 입력을 변수에 할당
        input_node = input().split()
        # 할당된 변수의 0번째 인덱스가 해당 노드의 번호
        arr[int(input_node[0])] = input_node

    print(f'#{tc}', calculate_inorder(1))