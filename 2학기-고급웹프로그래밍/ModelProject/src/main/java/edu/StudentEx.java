package edu;

public class StudentEx {

    public static void main(String[] args) {
        Student st1 = new Student();
        // 객체를 생성했지만 내용을 넣지 않았기 때문에 id, firstName, lastName 모두 Null을 출력
        System.out.println(st1);

        Student st2 = new Student(1L, "홍", "길동");
        System.out.println(st2); //1L, 홍, 길동을 출력하게 된다.
        st2.logInfo(); //firstName과 lastName을 구분하지않고 한 번에 이어서 출력한다.

        Student st3 = new Student(2L, "김", "동건");
        System.out.println(st3.getFirstName()); //st3의 firstName을 가져와서 출력한다 ("김")
        st3.setFirstName("이"); //getter, setter 어노테이션을 통해서 firstName이 '김'에서 '이'로 변경된다.
        System.out.println(st3);
        st3.logInfo();
    }
}
