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
        this.max_tokens = 1000; // 응답 최대 길이
        this.temperature = 0.7; // 창의성 설정
        this.messages = new ArrayList<>();

        // 현재 시간 계산 (ISO 8601 형식으로 포맷)
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // 프롬프트 추가
        String detailedPrompt = "현재 시간은 " + currentTime + "입니다. 다음 문장을 기반으로 JSON 일정 형식으로 변환하세요:\n\n" +
                "문장: \"" + prompt + "\"\n\n" +
                "JSON 형식:\n" +
                "{\n" +
                "    \"summary\": \"팀 회의\",\n" +
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
                "        {\"email\": \"example1@gmail.com\"},\n" +
                "    ],\n" +
                "    \"recurrence\": [\n" +
                "        \"RRULE:FREQ=WEEKLY;BYDAY=MO,WE,FR;UNTIL=20250131T235959Z\"\n" +
                "    ],\n" +
                "    \"priority\": <점수>\n" +
                "}\n\n" +
                "priority는 일정의 중요도를 나타냅니다. 중요한 일정인 경우 10점, 가벼운 일정인 경우 1점을 부여하세요. " +
                "일정의 성격에 따라 적절한 점수를 반환하세요.\n" +
                "위 형식만 반환하세요.";
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
