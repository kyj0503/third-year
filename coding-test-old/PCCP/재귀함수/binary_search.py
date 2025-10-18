nums = [1, 2, 3, 4, 5, 6, 7, 8, 9]

# low: 탐색 범위의 시작 인덱스
# high: 탐색 범위의 끝 인덱스
# target: 찾고자 하는 값
def binary_search(low, high, target): 
    if low > high: # 탐색이 불가능한 경우
        return '찾지 못함'

    # mid는 현재 탐색 범위의 중간 인덱스를 계산. 이진 탐색은 리스트를 반으로 나누어 탐색하기 때문에 중간 인덱스를 사용
    mid = (low + high) // 2

    if target == nums[mid]: # target값과 muns[mid]값이 같으면 mid 인덱스를 반환
        return mid
    elif target < nums[mid]: # target값이 nums[mid]값보다 작으면 mid보다 작은 범위를 탐색
        return binary_search(low, mid-1, target)
    elif target > nums[mid]: # target값이 nums[mid]값보다 크면 mid보다 큰 범위를 탐색
        return binary_search(mid+1, high, target)

# binary_search 함수 호출해서 리스트에서 7을 찾는다. 초기 탐색 범위는 리스트 전체(0부터 len(nums)-1)
print(binary_search(0, len(nums)-1, 7))