def heapify(arr, n, i):
    largest = i  # 루트
    left = 2 * i + 1  # 왼쪽 자식
    right = 2 * i + 2  # 오른쪽 자식

    # 왼쪽 자식이 루트보다 크다면
    if left < n and arr[largest] < arr[left]:
        largest = left

    # 오른쪽 자식이 현재까지 가장 큰 값보다 크다면
    if right < n and arr[largest] < arr[right]:
        largest = right

    # 가장 큰 값이 루트가 아니라면 교환하고 재귀적으로 힙 속성을 유지
    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        heapify(arr, n, largest)

def heap_sort(arr):
    n = len(arr)

    # 배열을 최대 힙으로 변환
    for i in range(n // 2 - 1, -1, -1):
        heapify(arr, n, i)

    # 힙에서 요소를 하나씩 추출하여 정렬
    for i in range(n - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # 루트(최대값)를 배열의 끝으로 이동
        heapify(arr, i, 0)  # 힙 크기를 줄이고 힙 속성을 유지

if __name__ == "__main__":
    # 예제 사용법
    arr = [12, 11, 13, 5, 6, 7]
    print("정렬 전 배열:", arr)
    heap_sort(arr)
    print("정렬 후 배열:", arr)


