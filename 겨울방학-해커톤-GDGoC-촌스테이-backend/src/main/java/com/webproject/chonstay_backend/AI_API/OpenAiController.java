package com.webproject.chonstay_backend.test;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.webproject.chonstay_backend.attraction.Attraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

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
            List<Attraction> atts = ParsingLocation(response.getBody());

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
                {"role": "system", "content": "You are a helpful assistant that identifies nearby 5 attractions based on a given address in South Korea. Respond only with the names of the attractions and their coordinates (latitude and longitude) in the format: {\\"name\\", latitude, longitude}, {\\"name\\", latitude, longitude}. name is Korean."},
                {"role": "user", "content": "%s"}
            ],
            "max_tokens": 300
        }
        """.formatted(prompt);
    }

    private List<Attraction> ParsingLocation(String body) {
        JSONObject jsonResponse = new JSONObject(body);
        JSONArray choices = jsonResponse.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");

        List<Attraction> attactions = new ArrayList<>();
        String[] locations = content.split("}, \\{");
        for (String location : locations) {
            location = location.replaceAll("[{}\"]", ""); // {, }, " 제거
            String[] parts = location.split(", ");
            String name = parts[0];
            double latitude = Double.parseDouble(parts[1]);
            double longitude = Double.parseDouble(parts[2]);

            System.out.printf("{\"%s\", %.6f, %.6f}%n", name, latitude, longitude);

            attactions.add(new Attraction(name, latitude, longitude));
        }
        return attactions;
    }
}
