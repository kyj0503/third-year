package com.example.JPAmemberProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //DB 연동을 처리하는 Interface
//지금 이게 Custom Repository네
public interface MemberRepository extends JpaRepository<Member, Long> {
    //Member를 상속받아서 관련 Member를 사용한다.
    //Hibernate가 기본적인 CRUD( findAll() )같은 메소드를 가져온다.
    //userId를 기반으로 Member 가져오기
    //jpaRepository를 통해서 Member를 상속받았기 때문에 여기에는 Custom Repository를 작성한다.

    Optional<Member> findByUserId(String userId);
        //select * from member where userId = userId; //JPA에서 Query 생성

    //email을 기반으로 Member 가져오기
    Optional<Member> findByEmail(String email);
        //select * from member where email = email

    /*
    @Query("select * from member where email = email")
    Optional<List<Member>> findAllByEmail2(@Param("email") String email);
    */

    //20대인 Member 리스트 가져오기
    @Query("SELECT m from Member m where m.age between 20 and 29")
    List<Member> twnetyGenerateMembers();

    //XX대인 Member 리스트 가져오기
    //변수를 사용하고 싶으면 :변수명 이런식으로 작성 해야한다. (이 문장에선 between 뒤에 오는 값들)
    @Query("SELECT m from Member m where m.age between :startAge and :endAge")
    List<Member> getGenerationMembers(@Param("startAge") int startAge, @Param("endAge")int endAge);

}
