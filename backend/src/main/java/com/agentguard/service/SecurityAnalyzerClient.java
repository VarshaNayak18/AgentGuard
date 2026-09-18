package com.agentguard.service;

import com.agentguard.dto.SecurityAnalysisRequest;
import com.agentguard.dto.SecurityAnalysisResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SecurityAnalyzerClient {

    private final RestTemplate restTemplate;

    public SecurityAnalyzerClient() {
        this.restTemplate = new RestTemplate();
    }

    public SecurityAnalysisResponse analyze(
        String tool,
        String action,
        String parameters) {

    SecurityAnalysisRequest request =
        new SecurityAnalysisRequest(tool, action, parameters);

    try {
        return restTemplate.postForObject(
            "http://localhost:8001/analyze",
            request,
            SecurityAnalysisResponse.class
        );

    } catch (Exception e) {

        SecurityAnalysisResponse fallback =
            new SecurityAnalysisResponse();

        fallback.setRisk_level("MEDIUM");
        fallback.setReason(
            "AI security analyzer unavailable; deterministic risk assessment used."
        );
        fallback.setConfidence(0.0);

        fallback.setPrompt_tokens(0);
        fallback.setCompletion_tokens(0);
        fallback.setTotal_tokens(0);
        fallback.setLatency_ms(0.0);

        return fallback;
    }
}
}