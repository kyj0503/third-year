package com.webproject.kangneng_back.openai.repository;

import com.webproject.kangneng_back.openai.entity.OpenAIResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenAIResponseRepository extends JpaRepository<OpenAIResponse, Long> {
}
