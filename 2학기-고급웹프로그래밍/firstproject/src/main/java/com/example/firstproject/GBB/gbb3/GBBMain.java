package com.example.firstproject.GBB.gbb3;

import java.util.Scanner;

//switch case, 3항 연산자
public class GBBMain {

    public static void main(String[] args) {

        GBB game = new GBB();
        Scanner sc = new Scanner(System.in);
        System.out.print("0~2(0:가위, 1:바위, 2:보) 사이의 숫자를 입력하시오 => ");
        int my = sc.nextInt();
        int com = (int) (Math.random() * 3);

        //화면에 사용자, 컴퓨터가 낸 것을 한글로 출력
        System.out.println("my: " + game.toGBB(my));
        System.out.println("com: " + game.toGBB(com));

        //결과 출력
        System.out.println(game.toDecision(game.compareDIff(my, com)));
    }
}

class GBB {
    static final int GBB_GAWI = 0;
    static final int GBB_BAWI = 1;
    static final int GBB_BO = 2;

    static final int GBB_SAME = 0;
    static final int GBB_WIN = 1;
    static final int GBB_LOSE = 2;

    public GBB() {
        System.out.println("가위바위보 게임을 시작합니다!");
    }

    //toGBB: 사용자, 컴퓨터가 낸 것을 문자열로 출력하는 메소드
    //3항 연산자
    String toGBB(int type) {
        return (type == GBB_GAWI)? "가위" : (type == GBB_BAWI) ? "바위" : "보";
    }

    //toDecision: 결과를 한글로 출력
    //3항 연산자
    String toDecision(int type) {
        return (type == GBB_SAME) ? "비김": (type == GBB_LOSE) ? "짐" : "이김";
    }

    //compareDiff: 게임 결과 산출   (0, 1, 2)
    //switch case
    int compareDIff(int my, int com) {
        int result = -1;

        int diff = my - com;
        switch (diff) {
            case 0:
                result = GBB_SAME; break;
            case -2:
            case 1:
                result = GBB_WIN; break;
            case -1:
            case 2:
                result = GBB_LOSE; break;
            default: result = -1;
        }

        return result;
    }
}