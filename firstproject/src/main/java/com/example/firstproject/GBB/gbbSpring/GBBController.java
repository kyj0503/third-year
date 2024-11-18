package com.example.firstproject.GBB.gbbSpring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class GBBController {

    public GBBController() {
        System.out.println("Hello GBB Controller~~ ^^^^^^^^");
    }

    @GetMapping("/gbb/{my}")
    @ResponseBody
    public String gbb(@PathVariable("my") int my){
        String html = "";
        //GBBGame의 객체 생성
        GBBGame game = new GBBGame("가위.png, 바위.png, 보.png", "draw.jpg, win.jpg, lose.jpg");

        game.run(my);

        String myImg = game.showMyImg(); //사용자로부터 받은 값으로 이미지 생성
        String comImg = game.showComImg(); //컴퓨터의 이미지 생성
        String result = game.getDetImg(); //승패 이미지 생성

        String centerDiv = "display: flex; justify-content: center; align-item: center; height: 600;";

        html += "<h2>게임 결과</h2>";
        html += String.format("<div style='%s'>", centerDiv);
        html += "내가 낸것: ";
        html += String.format("<img src='/img/%s' width='225' height='225'>", myImg);
        html += "컴퓨터가 낸것: ";
        html += String.format("<img src='/img/%s' width='225' height='225'>", comImg);
        html += "결과: ";
        html += String.format("<img src='/img/%s' width='161' height='500'><br>", result);
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

    public GBBGame(String gbb, String det) {
        //이미지를 가져올 때 ,를 기준으로 없애고 양옆에 공백을 삭제함
        for(String img: gbb.split(",")){
                gbbImg.add(img.trim());
        }

        for(String img: det.split(",")){
            detImg.add(img.trim());
        }
    }
    //컴퓨터랑 사용자의 값 생성
    public void run(int my) {
        this.my = my;
        getCom();
    }
    public void getCom(){
        this.com = (int) (Math.random() * 3);
    }

    //승패를 결정하는 메소드로, 기존 콘솔버전에서 사용하던 논리 Or연산자를 사용하지않고 간단한 수식으로 표현
    public int detResult() { return (my - com + 3) % 3; }

    //ArrayList에서 사용자와 컴퓨터의 값에 해당하는 이미지 URL을 가져온다.
    public String showMyImg() { return gbbImg.get(my); }
    public String showComImg() { return gbbImg.get(com); }

    public String getDetImg(){
        return detImg.get(detResult());
    }
}