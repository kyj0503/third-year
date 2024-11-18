package com.example.boardproject.Repository;

import com.example.boardproject.Entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    public List<Member> findAll();
}
