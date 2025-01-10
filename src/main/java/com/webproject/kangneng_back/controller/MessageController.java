package com.webproject.kangneng_back.controller;

import com.webproject.kangneng_back.entity.Message;
import com.webproject.kangneng_back.repository.MessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageRepository messageRepository;

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        messageRepository.save(message);
        return ResponseEntity.ok("Message saved successfully!");
    }
}
