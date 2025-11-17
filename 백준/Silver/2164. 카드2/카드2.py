from collections import deque
N = int(input())
# 1 ~ N까지 큐에 담음
q = deque([i for i in range(1, 1 + N)])

# True일때는 카드 버리기, False일때는 뒤로 미루기
flag = True
# 하나만 남을 때 까지 반복
while len(q) > 1:
    # 큐에서 값을 하나 꺼냄
    num = q.popleft()
    # flag에 따라 분기
    if flag:
        flag = False
        continue
    else:
        flag = True
        q.append(num)

print(q[0])