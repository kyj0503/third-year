package edu;

public class StudentEx {
    public static void main(String[] args) {

        Student st1 = new Student();
        System.out.println(st1);

        Student st2 = new Student(1L, "홍", "길동");
        System.out.println(st2);
        st2.logInfo();

        Student st3 = new Student(2L, "김", "동건");
        System.out.println(st3.getFirstName());
        st3.setFirstName("이");
        System.out.println(st3);
        st2.logInfo();


    }
}
