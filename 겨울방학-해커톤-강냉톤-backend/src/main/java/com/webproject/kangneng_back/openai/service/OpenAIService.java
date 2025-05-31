package com.webproject.kangneng_back.openai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webproject.kangneng_back.openai.dto.ChatGPTRequest;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Jackson ObjectMapper 사용

    @Async
    public CompletableFuture<String> getOpenAIResponseAsync(ChatGPTRequest request) {
        try {
            String response = getOpenAIResponse(request); // 기존 동기 메서드 호출
            return CompletableFuture.completedFuture(response);
        } catch (IOException e) {
            e.printStackTrace();
            return CompletableFuture.failedFuture(e);
        }
    }

    public String getOpenAIResponse(ChatGPTRequest request) throws IOException {
        // 요청 본문을 JSON 문자열로 변환
        String requestBodyJson = objectMapper.writeValueAsString(request);

        // OkHttpClient 설정: 타임아웃 시간 늘리기
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃
                .writeTimeout(30, TimeUnit.SECONDS)  // 요청 타임아웃
                .readTimeout(30, TimeUnit.SECONDS)   // 응답 타임아웃
                .build();

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
                throw new IOException("Unexpected code " + response.code() + ": " + response.body().string());
            }
            return response.body().string(); // 응답 데이터 반환
        }
    }
}
