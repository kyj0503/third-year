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
            // 서비스 호출
            String jsonResponse = openAIService.getOpenAIResponse(naturalLanguage);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing request: " + e.getMessage());
        }
    }
}
