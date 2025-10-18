# 메모이제이션은 이미 계산된 값을 저장해 두고, 동일한 계산을 반복하지 않도록 하는 기법
# memo 리스트는 피보나치 수열의 값을 저장. 초기값으로 0과 1 설정. 피보나치 수열의 첫 두 항이다.
memo = [0, 1]

def fibo(n):
    if n >= 2 and n >= len(memo):
        memo.append(fibo(n-1) + fibo(n-2))
    return memo[n]

print(fibo(10))