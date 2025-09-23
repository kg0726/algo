def update_max_score(score):
    global max_score
    max_score = max(max_score, score)

def progress_inning(batter_perm, arr, N):
    # 만들어진 타자 순열을 인자로 받아 이닝의 진행 결과를 반환하는 함수
    inning = 0
    score = 0
    batter_idx = 0  # 타순 인덱스 (0~8)

    while inning < N:
        out = 0
        base1, base2, base3 = 0, 0, 0

        while out < 3:  # 3아웃 될 때까지 진행
            now_batter = batter_perm[batter_idx]
            result = arr[inning][now_batter]

            if result == 0:  # 아웃
                out += 1

            elif result == 1:  # 안타
                if base3: score += 1
                base1, base2, base3 = 1, base1, base2

            elif result == 2:  # 2루타
                if base2: score += 1
                if base3: score += 1
                base1, base2, base3 = 0, 1, base1

            elif result == 3:  # 3루타
                score += base1 + base2 + base3
                base1, base2, base3 = 0, 0, 1

            elif result == 4:  # 홈런
                score += base1 + base2 + base3 + 1
                base1, base2, base3 = 0, 0, 0

            # 다음 타자로
            batter_idx = (batter_idx + 1) % 9

        inning += 1

    return score


def make_permutation(arr, batter, n, N):
    # 종료조건 순열이 9개가 되면 종료
    if n == 9:
        # 이닝을 진행
        score = progress_inning(batter_perm, arr, N)
        # 최댓값 비교
        update_max_score(score)
        return
    # 4번타자 추가
    if n == 3:
        batter_perm.append(0)
        make_permutation(arr, batter, n + 1, N)
        batter_perm.pop()
    # arr 을 순회하며 순열을 생성
    for i in batter:
        # i가 포함되어 있지 않다면 추가함
        if not use[i]:
            use[i] = True
            # 순열에 추가
            batter_perm.append(i)
            # 재귀 호출
            make_permutation(arr, batter, n + 1, N)
            # 백트래킹
            use[i] = False
            batter_perm.pop()

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

# 타자의 배열 생성 1 ~ 8 번까지 (4번타자 고정은 0번 인덱스는 함수에서 추가할 예정)
batter = [i for i in range(1, 9)]
use = [False] * 9
batter_perm = []
max_score = 0

make_permutation(arr, batter, 0, N)
print(max_score)