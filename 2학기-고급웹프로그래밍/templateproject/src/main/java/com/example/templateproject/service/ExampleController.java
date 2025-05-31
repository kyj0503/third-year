package com.example.templateproject.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ExampleController {

    //각 원소를
    @GetMapping("/objectx")
    public String person(Model model) {
        //Person이라는 Class의 메소드를 통해서 각 name, age, address에 값을 추가한 객체를 만든다.
        Person person1 = new Person("아이유", 30, "서울");
        Person person2 = new Person("이세영", 25, "광주");

        //model 객체에 person객체를 넣는다. (name, age, address의 매개변수가 View로 전달된다)
        model.addAttribute("person1", person1);
        model.addAttribute("person2", person2);

        return "Person"; //Templates에 있는 Person.mustache로 파일을 렌더링 후 person값을 보낸다
    }

    //Mustache의 if(조건절)
    @GetMapping("/conditionex")
    public String login(Model model) {
        boolean logedIn = false; //로그인 유무를 판단하기 위해서 사용
        model.addAttribute("username", "아이유");

        // Mustache에서 {{#logedIn != false}}일 때, 로그인을 승인해준다.
        model.addAttribute("logedIn", logedIn);

        return "conditionex";
    }
    //Mustache의 for(반복절)
    @GetMapping("/loopx")
    public String getPersonList(Model model) {
        String[] fruits = {"apple", "banana", "orange"};
        List<Person> persons = Arrays.asList(
                new Person("아이유", 25, "서울"),
                new Person("유재석", 50, "부산"),
                new Person("안세영", 20, "광주")
        );

        model.addAttribute("fruits", fruits); //둘 중 하나를 빼면 그 부분이 아예 비어지게 보인다.
        model.addAttribute("persons", persons);
        return "loopx";
    }

    @GetMapping("/partialex")
    public String partialex(Model model) {
        String[] buttons = {"Cancel", "Save", "Insert"};
        model.addAttribute("buttons", buttons);
        return "partialex";
    }

    @GetMapping("/show-input")
    public String showInput(Model model) {
        String userInput = "<b>안녕하세요! 이 텍스트는 굵게 표시됩니다.</b>";
        model.addAttribute("userInput", userInput);
        return "no-escape";
    }

}
