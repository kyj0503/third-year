package com.webproject.chonstay_backend.AI_API;

import com.webproject.chonstay_backend.attraction.Attraction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

// OpenAiService 클래스 정의
public class OpenAiService {
    @Value("${openai.api.key}")
    private String openAiApiKey;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public List<Attraction> parseAttractions(String prompt) {
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
            return ParsingLocation(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
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
