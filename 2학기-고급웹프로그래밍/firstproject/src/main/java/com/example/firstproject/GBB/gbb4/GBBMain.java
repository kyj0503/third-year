package com.example.firstproject.GBB.gbb4;

import java.util.Scanner;

//Array 이용. 입력 예외처리
public class GBBMain {

    public static void main(String[] args) {

        String[] gbb = {"가위", "바위", "보"};
        String[] det = {"비김", "이김", "짐"};

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("0~2(0:가위, 1:바위, 2:보) 사이의 숫자를 입력하시오 => ");
            int my = sc.nextInt();

            //예외 처리
            if (my < 0 || my > 2) {
                if (my == -1) {
                    System.out.println("가위바위보 게임을 종료합니다");
                    break;
                } else {
                    System.out.println("잘못된 수입니다. 0에서 2사이의 정수를 입력하세요.!!!");
                    continue;
                }
            }

            int com = (int) (Math.random() * 3);

            //화면에 사용자, 컴퓨터가 낸 것을 한글로 출력
            System.out.println("내가 낸 것: " + gbb[my] + ", 컴이 낸 것: " + gbb[com]);

            //결과 출력
            String result = "";

            if (my == com) result = det[0];      //비김
            else if (my == 0 && com == 2 || my == 1 && com == 0 || my == 2 && com == 1) result = det[1];  //이김
            else if (my == 0 && com == 1 || my == 1 && com == 2 || my == 2 && com == 0) result = det[2];  //짐

            System.out.println("결과: " + result);
            System.out.println("=======================================");
            System.out.println();
        }
    }
}