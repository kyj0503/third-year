package com.webproject.kangneng_back.openai.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChatGPTRequest {
    private String model;
    private List<Message> messages;
    private int max_tokens;
    private double temperature;

    public ChatGPTRequest(String prompt) {
        this.model = "gpt-4"; // 사용하는 모델 설정
        this.max_tokens = 500; // 응답 최대 길이를 500으로 줄임
        this.temperature = 0.7; // 창의성 설정
        this.messages = new ArrayList<>();

        // 현재 시간 계산 (ISO 8601 형식으로 포맷)
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // 프롬프트 추가
        String detailedPrompt = "현재 시간은 " + currentTime + "입니다. 다음 문장을 기반으로 순수 JSON 데이터만 반환한다. 추가적인 텍스트는 금지한다. :\n\n" +
                "문장: \"" + prompt + "\"\n\n" +
                "JSON 형식:\n" +
                "{\n" +
                "    \"title\": \"팀 회의\",\n" +
                "    \"description\": \"주간 진행 상황 논의\",\n" +
                "    \"location\": \"서울특별시 강남구\",\n" +
                "    \"start\": {\n" +
                "        \"dateTime\": \"2025-01-10T10:00:00+09:00\",\n" +
                "        \"timeZone\": \"Asia/Seoul\"\n" +
                "    },\n" +
                "    \"end\": {\n" +
                "        \"dateTime\": \"2025-01-10T11:00:00+09:00\",\n" +
                "        \"timeZone\": \"Asia/Seoul\"\n" +
                "    },\n" +
                "    \"attendees\": [\n" +
                "        {\"name\": \"홍길동\"},\n" +
                "        {\"name\": \"김철수\"}\n" +
                "    ],\n" +
                "    \"priority\": 10\n" +
                "}\n\n" +
                "JSON 형식은 위와 같이 반환한다. " +
                "입력 받지 않은 정보는 null로 한다. (대문자 NULL 사용 금지) " +
                "attendees는 참석자 이름으로 한다. 여러명이 가능하다. " +
                "priority는 일정의 중요도를 나타낸다. 정수 1부터 10까지 책정할 수 있다." +
                "매우 중요한 일정은 10점, 중요도가 낮은 일정은 1점으로 한다. " +
                "일정까지 남은 시간과 사건의 종류를 기반으로 priority를 정한다. \n\n" +
                "위 JSON 형식만 반환한다.";

        this.messages.add(new Message("user", detailedPrompt));
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
