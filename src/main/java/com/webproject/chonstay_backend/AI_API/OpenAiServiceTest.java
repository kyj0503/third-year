package com.webproject.chonstay_backend.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class OpenAiController {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private final WebClient webClient;

    public OpenAiController(WebClient.Builder webClientBuilder) { // 생성자에서 
        this.webClient = webClientBuilder.build();
    }

    @PostMapping("/api/AI/recommend")
    public Mono<String> getRecommendation(@RequestBody String prompt) {
        return webClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + openAiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(buildRequest(prompt)) // OpenAi API에 요청할 JSON 데이터 생성
                .retrieve()
                .bodyToMono(String.class); // 응답을 String으로 변환
    }

    private String buildRequest(String prompt) {
        return """
        {
            "model": "gpt-4",
            "messages": [
                {"role": "system", "content": "You are a helpful assistant."},
                {"role": "user", "content": "%s"}
            ],
            "max_tokens": 100
        }
        """.formatted(prompt);
    }
}
