package com.webproject.chonstay_backend.test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/AI")
public class OpenAiController {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @PostMapping("/recommend/")
    public ResponseEntity<String> getRecommendation(@RequestBody String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("Authorization", "Bearer " + openAiApiKey);
                return execution.execute(request, body);
            });

            // 요청 본문 생성
            String requestBody = buildRequest(prompt);

            // HTTP 요청 엔티티 생성
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // OpenAI API 호출
            ResponseEntity<String> response = restTemplate.postForEntity(OPENAI_API_URL, entity, String.class);

            // OpenAI 응답 반환
            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
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
