# import sys
# sys.stdin = open('swim.txt')
# 0123 순서로 일, 달, 3달, 연 이용권
ticket = [0, 1, 2, 3]
T = int(input())
def buy_ticket(month, cost):
    global min_cost
    # 비용 초기화

    # 마지막 월이 된다면
    if month == 12:
        # 비용 갱신
        if min_cost > cost:
            min_cost = cost

    # 마지막 월의 인덱스 번호가 아니면
    if month < 12:
        # 해당 월부터 가능한 모든 구성을 해본다
        for i in range(3):
            # 일일 이용권을 이용하는 경우
            if i == 0:
                # 다음달로 넘어감
                buy_ticket(month + 1, cost + arr[month] * day)
            # 월 이용권을 이용하는 경우
            elif i == 1:
                # 해당 달에 이용할 계획이 없는 경우
                if arr[month] == 0:
                    buy_ticket(month + 1, cost)
                else:
                    # 다음달로 넘어감
                    buy_ticket(month + 1, cost + month_fee)
            # 3달 이용권을 이용하는 경우
            elif i == 2:
                # 3달짜리 이용권을 구매한 경우 10월 이상일 경우 인덱스 에러가 발생할 수 있음
                if month > 8:
                    buy_ticket(12, cost + three_month)
                else:
                    buy_ticket(month + 3, cost + three_month)




for tc in range(1, 1 + T):
    day, month_fee, three_month, year = list(map(int, input().split()))
    arr = list(map(int, input().split()))
    min_cost = float('inf')
    buy_ticket(0, 0)
    # 연간 이용권을 사용했을때와 비교
    if year < min_cost:
        min_cost = year

    print(f'#{tc} {min_cost}')