#백준 2559번 수열 문제

N, K = map(int, input().split()) # N: 온도를 측정할 날 수 K: 합을 구하기 위한 연속적인 날 수
arr = list(map(int, input().split()))
answer = cnt = sum(arr[:K]) #첫 K개의 원소 합

for i in range(N-K):
    cnt = cnt - arr[i] + arr[i+K]
    if answer < cnt:
        answer = cnt

print(answer)