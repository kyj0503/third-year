import sys
input = sys.stdin.readline

N, M = map(int, input().split())
nums = list(map(int, input().split()))

for i in range(1, len(nums)):  # 누적합으로 바꾸기
    nums[i] += nums[i-1]

# for 문에서 _ 사용은 인덱스를 무시하기 위해서 사용
for _ in range(M):
    s, e = map(int, input().split()) # 각 쿼리의 시작 인덱스 s 끝 인덱스 e
    subtract = 0 if s == 1 else nums[s-2] # 삼항 연산자로 s가 1이면 0, 아니면 s-1까지의 합
    print(nums[e-1] - subtract) # e까지의 합에서 s-1까지의 합을 빼면 s부터 e까지의 합이 나온다