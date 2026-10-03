package com.IntBuddy.IntBuddy.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AIService {

    private final RestTemplate restTemplate;

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.url}")
    private String url;

    @Value("${groq.model}")
    private String model;

    public AIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String askAI(String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", message
                        )
                )
        );

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(body, headers);

        System.out.println("Groq URL = " + url);
        System.out.println("API Key Present = " +
                (apiKey != null && !apiKey.isBlank()));

        Map response =
                restTemplate.postForObject(url, entity, Map.class);

        List choices = (List) response.get("choices");
        Map choice = (Map) choices.get(0);
        Map msg = (Map) choice.get("message");

        return msg.get("content").toString();
    }

    public String getModels() {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.groq.com/openai/v1/models",
                HttpMethod.GET,
                entity,
                String.class
        );

        return response.getBody();
    }
