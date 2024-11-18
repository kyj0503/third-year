package com.example.firstproject.GBB.gbb2;


import java.util.Scanner;

//method style, if-else, 상수
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

class GBB{

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
    String toGBB(int type) {
        String result = "";
        if (type == GBB_GAWI) result = "가위";
        else if (type == GBB_BAWI) result = "바위";
        else if (type == GBB_BO) result = "보";
        return result;
    }

    //toDecision: 결과를 한글로 출력
    String toDecision(int type) {
        String result = "";
        if(type == GBB_SAME) result = "비김";
        else if(type == GBB_WIN) result = "이김";
        else if(type == GBB_LOSE) result = "짐";

        return result;
    }

    //compareDiff: 게임 결과 산출   (0, 1, 2)
    int compareDIff(int my, int com) {
        int result = -1;

        int diff = my - com;
        if (diff == 0) result = GBB_SAME;
        else if (diff == -2 || diff == 1) result = GBB_WIN;
        else if (diff == -1 || diff == 2) result = GBB_LOSE;
        else result = -1;

        return result;

    }

}
