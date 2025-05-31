package edu;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

//lombok: 코드를 줄이는 라이브러리로, 어노테이션을 통해서 지정된 메소드를 가져온다.
@Getter @Setter //Getter, Setter 어노테이션으로 값들을 가져오고 설정하는 getter, setter를 자동으로 생성
@ToString //toString()이라는 값을 문자열로 변환하는 메서드를 자동으로 생성
@NoArgsConstructor @AllArgsConstructor //기본 생성자, 모든 필드를 매개변수로 받는 생성자를 생성하는 어노테이션
@Slf4j //로그를 쉽게 확인하고 사용할 수 있는 Logger 객체를 자동으로 생성한다.

public class Student {
    private Long id;
    private String firstName;
    private String lastName;

    public void logInfo() {
        log.info("student id: {}, name: {}{}", id, firstName, lastName);
    }
}