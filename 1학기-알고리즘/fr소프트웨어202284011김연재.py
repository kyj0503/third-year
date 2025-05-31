from tkinter import *
import random

# 원 모양 프랙탈 그리는 함수를 삼각형 모양 프랙탈 그리는 함수로 변경
def drawSierpinski(x, y, r, colors):
    if r < 20:
        # 최소 크기에 도달하면 더 이상 쪼개지지 않음
        return
    
    # 삼각형의 세 꼭지점을 계산
    points = [x, y - r,
              x - (r * (3 ** 0.5)) / 2, y + r / 2,
              x + (r * (3 ** 0.5)) / 2, y + r / 2]
    
    # 삼각형 그리기
    color = colors[0]
    canvas.create_polygon(points, outline='black', fill=color, width=1)
    
    # 재귀 호출에 동일한 색상이 접촉하지 않도록 새로운 색상 목록 준비
    new_colors = colors[1:] + [colors[0]]
    
    # 작은 삼각형 그리기
    drawSierpinski(x, y - r / 2, r / 2, new_colors)
    drawSierpinski(x - (r * (3 ** 0.5)) / 4, y + r / 4, r / 2, new_colors)
    drawSierpinski(x + (r * (3 ** 0.5)) / 4, y + r / 4, r / 2, new_colors)

# 전역 변수 선언 부분
colors = ["red", "green", "blue", "yellow", "orange", "indigo", "violet"]
wSize = 500
radius = 200

# 메인 코드
window = Tk()
window.title("Sierpinski Triangle")
canvas = Canvas(window, height=wSize, width=wSize, bg='white')

drawSierpinski(wSize / 2, wSize / 2, radius, colors)

canvas.pack()
window.mainloop()


