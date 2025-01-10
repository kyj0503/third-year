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

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getOpenAIResponse(String naturalLanguage) throws IOException {
        // 프롬프트 생성
        ChatGPTRequest request = new ChatGPTRequest(naturalLanguage);

        // 요청 본문을 JSON으로 변환
        String requestBodyJson = objectMapper.writeValueAsString(request);

        // OpenAI API 호출
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(
                requestBodyJson, MediaType.get("application/json")
        );

        Request httpRequest = new Request.Builder()
                .url(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response: " + response.body().string());
            }
            return response.body().string();
        }
    }
}
