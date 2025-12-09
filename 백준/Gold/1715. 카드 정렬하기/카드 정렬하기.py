from heapq import heappop, heappush, heapify
N = int(input())
arr = [int(input()) for _ in range(N)]

result = 0
# 모두 더해져서 마지막 하나만 남을 때 까지 진행
# 힙 정렬
heapify(arr)
while len(arr) != 1:

    # 가중치가 가장 낮은 카드 두개를 꺼냄
    card_a = heappop(arr)
    card_b = heappop(arr)

    mix = card_b + card_a
    # 다시 큐에 넣음
    heappush(arr, mix)
    result += mix

print(result)