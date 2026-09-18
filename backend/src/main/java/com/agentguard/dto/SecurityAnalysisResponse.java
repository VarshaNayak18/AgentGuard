package com.agentguard.dto;

public class SecurityAnalysisResponse {

    private String risk_level;
    private String reason;
    private Double confidence;

    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;

    public String getRisk_level() {
        return risk_level;
    }

    public String getReason() {
        return reason;
    }

    public Double getConfidence() {
        return confidence;
    }

    public Integer getPrompt_tokens() {
        return prompt_tokens;
    }

    public Integer getCompletion_tokens() {
        return completion_tokens;
    }

    public Integer getTotal_tokens() {
        return total_tokens;
    }
}