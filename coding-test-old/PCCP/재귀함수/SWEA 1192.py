# 입력 받기
T = int(input())

# 가격 비교 함수
def find_cheapest(month, acc):
    global answer

    # 백트래킹 정의. 이미 너무 비싼 루트면 가지치기
    if acc >= answer:  # 현재 누적 비용 acc이 이미 최적 비용 answer보다 크거나 같으면 더 이상 탐색을 하지 않고 종료
        return

    if month > 11:  # month 범위 벗어난 경우
        if answer > acc:  # 누적 비용 acc이 최적비용 answer보다 작으면 갱신
            answer = acc
        return  

    # 1일권 plan 수만큼 사서 다음달로
    find_cheapest(month+1, acc + plans[month]*prices[0])

    # 1달권 사서 다음달로
    find_cheapest(month+1, acc + prices[1])

    # 3달권 사서 다음달로
    find_cheapest(month+3, acc + prices[2])

# 테스트 케이스 처리
for tc in range(1, T+1):
    prices = list(map(int, input().split()))
    plans = list(map(int, input().split()))
    answer = prices[3]  # answer를 1년권 가격으로 초기화한다.
    find_cheapest(0, 0)  # find_cheapest 함수를 사용해서 최적의 비용을 찾는다.

    print('#{} {}'.format(tc, answer))