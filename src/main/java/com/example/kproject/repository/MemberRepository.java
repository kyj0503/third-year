package com.example.kproject.repository;

import com.example.kproject.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<Member> findByUserId(String userId);

    @Query("SELECT m from Member m where m.age between 20 and 29")
    List<Member> twentyGenerateMembers();

    @Query("SELECT m from Member m where m.age between :startAge and :endAge")
    List<Member> getGenerationMembers(@Param("startAge") int startAge, @Param("endAge")int endAge);

}
