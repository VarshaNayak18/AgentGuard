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
                new SecurityAnalysisRequest(
                        tool,
                        action,
                        parameters
                );

        return restTemplate.postForObject(
                "http://localhost:8001/analyze",
                request,
                SecurityAnalysisResponse.class
        );
    }
}