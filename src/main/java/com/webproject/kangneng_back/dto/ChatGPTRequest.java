package com.webproject.kangneng_back.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;
    private int max_tokens; // 수정: maxTokens -> max_tokens
    private double temperature;

    public ChatGPTRequest(String prompt) {
        this.model = "gpt-3.5-turbo";
        this.max_tokens = 100; // 수정된 필드 이름
        this.temperature = 0.7;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

    @Data
    public static class Message {
        private String role;
        private String content;

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
