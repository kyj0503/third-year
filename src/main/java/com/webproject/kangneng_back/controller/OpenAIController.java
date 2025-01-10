package com.webproject.kangneng_back.controller;

import com.webproject.kangneng_back.dto.ChatGPTRequest;
import com.webproject.kangneng_back.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody String prompt) {
        try {
            ChatGPTRequest request = new ChatGPTRequest(prompt);
            String answer = openAIService.getOpenAIResponse(request);
            return ResponseEntity.ok(answer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error while connecting to OpenAI: " + e.getMessage());
        }
    }
}
