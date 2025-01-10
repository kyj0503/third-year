package com.webproject.kangneng_back.repository;

import com.webproject.kangneng_back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
