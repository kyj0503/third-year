package com.webproject.kangneng_back.openai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webproject.kangneng_back.openai.dto.ChatGPTRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper 사용

    public String getOpenAIResponse(ChatGPTRequest request) throws IOException {
        // 요청 본문을 JSON 문자열로 변환
        String requestBodyJson = objectMapper.writeValueAsString(request);

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(
                requestBodyJson, MediaType.get("application/json")
        );

        Request httpRequest = new Request.Builder()
                .url(apiUrl) // OpenAI API URL
                .header("Authorization", "Bearer " + apiKey) // API 키 설정
                .header("Content-Type", "application/json") // 요청 데이터 형식 지정
                .post(requestBody)
                .build();

        // 요청 실행
        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + ": " + response.body().string());
            }
            return response.body().string(); // 응답 데이터 반환
        }
    }
}
