import random

def is_sorted(arr):
    for i in range(len(arr) - 1):
        if arr[i] > arr[i + 1]:
            return False
    return True

def bogo_sort(arr):
    attempts = 0
    while not is_sorted(arr):
        random.shuffle(arr)
        attempts += 1
    return attempts

if __name__ == "__main__":
    # Example usage
    arr = [10, 3, 9, 2, 5, 8, 1, 4, 6, 7]
    print("Original array:", arr)
    attempts = bogo_sort(arr)
    print("Sorted array:", arr)
    print(f"Number of shuffles: {attempts}")



