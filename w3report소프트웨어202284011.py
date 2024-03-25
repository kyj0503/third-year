## 함수 선언 부분 ## 
def printPoly(p_x):
    term = len(p_x) - 1  # 최고차항 숫자 = 배열길이-1
    polyStr = "P(x) = "

    for i in range(len(p_x)):
        coef = p_x[i]  # 계수

        if coef != 0:  # 계수가 0이 아닌 경우에만 출력
            if coef > 0:  # 양수인 경우에만 + 부호를 추가
                if i != 0:  # 첫 번째 항이 아닌 경우에만 + 부호를 추가
                    polyStr += "+"
            polyStr += str(coef) + "x^" + str(term) + " "
        term -= 1

    return polyStr

## 전역 변수 선언 부분 ## 
px = [7, -4, 0, 5]			# = 7x^3 -4x^2 +0x^1 +5x^0 


## 메인 코드 부분 ## 
if __name__ == "__main__" :

	pStr = printPoly(px)
	print(pStr)
