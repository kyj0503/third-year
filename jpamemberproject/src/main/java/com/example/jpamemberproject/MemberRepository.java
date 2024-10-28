package com.example.jpamemberproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /*
    Optional은 null 또는 값을 감싸서 NPE(NullPointerException)로부터 부담을 줄이기 위해 등장한 Wrapper 클래스이다.
    Optional은 값을 Wrapping하고 다시 풀고, null 일 경우에는 대체하는 함수를 호출하는 등의 오버헤드가 있으므로 잘못 사용하면 시스템 성능이 저하된다.
    그렇기 때문에 메소드의 반환 값이 절대 null이 아니라면 Optional을 사용하지 않는 것이 좋다.
    즉, Optional은 메소드의 결과가 null이 될 수 있으며, null에 의해 오류가 발생할 가능성이 매우 높을 때 반환값으로만 사용되어야 한다.

     */
    Optional<Member> findByUserId(String userId);
    // select * from member where userId = userId;

    // email 기반으로 Member 가져오기
    Optional<Member> findByEmail(String email);

    // 20대인 Member 리스트 가져오기
    @Query("SELECT m from Member m where m.age between 20 and 29")
    List<Member> twentyGenerateMembers();

    // XX대인 Member 리스트 가져오기
    @Query("SELECT m from Member m where m.age between :startAge and :endAge")
    List<Member> getGenerationMembers(@Param("startAge") int startAge, @Param("endAge")int endAge);

}
