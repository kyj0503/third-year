package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class GBBController {

    // GBBController 생성자
    // 클래스가 인스턴스화될 때 출력 메시지로 개발자에게 컨트롤러가 성공적으로 생성되었음을 알림
    public GBBController() {
        System.out.println("Hello GBB Controller~~ ^^^^^^^^");
    }

    // GetMapping을 통해 클라이언트로부터 요청된 경로 "/gbb/{my}"를 처리
    // {my}는 URL에서 동적으로 입력된 값으로, 사용자가 선택한 가위(0), 바위(1), 보(2)를 의미
    @GetMapping("/gbb/{my}")
    @ResponseBody
    public String gbb(@PathVariable("my") int my){

        // GBBGame 객체를 생성하며, 가위바위보와 관련된 이미지 파일 이름을 전달
        // 첫 번째 인자는 가위, 바위, 보에 대한 이미지
        // 두 번째 인자는 결과에 대한 이미지 (무승부, 승리, 패배)
        String html = "";
        GBBGame game = new GBBGame("gawi.jpg, bawi.jpg, bo.jpg", "draw.jpg, win.jpg, lose.jpg");

        // 사용자가 선택한 'my' 값을 게임 실행 메서드로 전달해 게임을 실행
        game.run(my);

        // 내 선택과 컴퓨터의 선택에 대한 이미지를 가져오고 결과 이미지를 설정
        String myImg = game.showMyImg();
        String comImg = game.showComImg();
        String result = game.getDetImg();

        // HTML 레이아웃을 중앙 정렬하는 CSS 스타일 선언 (Flexbox 이용)
        String centerDiv = "display: flex; justify-content: center; align-item: center; height: 600;";

        html += "<h2>게임 결과</h2>";
        html += String.format("<div style='%s'>", centerDiv);
        html += "내가 낸것: ";
        html += String.format("<img src='/img/gbb/%s' width='225' height='225'>", myImg);
        html += "컴퓨터가 낸것: ";
        html += String.format("<img src='/img/gbb/%s' width='225' height='225'>", comImg);
        html += "결과: ";
        html += String.format("<img src='/img/gbb/%s' width='225' height='225'><br>", result);
        html += "</div>";
        html += "<a href='/gbb.html'>다시 시작</a>";

        return html;
    }
}

class GBBGame {

    private int my;
    private int com;
    private ArrayList<String> gbbImg = new ArrayList<>();
    private ArrayList<String> detImg = new ArrayList<>();

    // GBBGame 생성자: 이미지 리스트 초기화
    // 전달받은 이미지 파일 이름들을 각각 가위바위보 이미지 리스트와 결과 이미지 리스트로 분리해 저장
    public GBBGame(String gbb, String det) {

        for(String img: gbb.split(",")){
                gbbImg.add(img.trim());
        }

        for(String img: det.split(",")){
            detImg.add(img.trim());
        }
    }

    public void run(int my) {
        this.my = my;
        // 컴퓨터의 선택을 생성하는 메서드를 호출
        getCom();
    }

    // 컴퓨터가 무작위로 가위(0), 바위(1), 보(2) 중 하나를 선택 (0부터 2 사이의 랜덤 값)
    public void getCom(){
        this.com = (int) (Math.random() * 3);
    }

    // 결과를 결정하는 로직: 사용자의 선택과 컴퓨터의 선택을 비교하여 승패 계산
    // 0 = 무승부, 1 = 승리, 2 = 패배를 나타냄
    public int detResult() {
        return (my - com + 3) % 3;
    }

    public String showMyImg() {
        return gbbImg.get(my);
    }

    public String showComImg() {
        return gbbImg.get(com);
    }

    public String getDetImg(){
        return detImg.get(detResult());
    }
}