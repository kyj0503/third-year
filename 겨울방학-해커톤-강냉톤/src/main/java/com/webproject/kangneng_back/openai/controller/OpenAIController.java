package com.webproject.kangneng_back.openai.controller;

import com.webproject.kangneng_back.openai.dto.ChatGPTRequest;
import com.webproject.kangneng_back.openai.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convertToJSON(@RequestBody String naturalLanguage) {
        try {
            // ChatGPTRequest 객체 생성
            ChatGPTRequest request = new ChatGPTRequest(naturalLanguage);

            // OpenAIService 호출
            String jsonResponse = openAIService.getOpenAIResponse(request);

            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing request: " + e.getMessage());
        }
    }
}
