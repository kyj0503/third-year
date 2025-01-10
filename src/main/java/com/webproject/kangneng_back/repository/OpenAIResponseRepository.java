package com.webproject.kangneng_back.repository;

import com.webproject.kangneng_back.entity.OpenAIResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenAIResponseRepository extends JpaRepository<OpenAIResponse, Long> {
}
