package com.agentguard.dto;

public class SecurityAnalysisResponse {

    private String risk_level;
    private String reason;
    private Double confidence;

    private Integer prompt_tokens;
    private Integer completion_tokens;
    private Integer total_tokens;
    private Double latency_ms;

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

    public Double getLatency_ms() {
        return latency_ms;
    }

    public void setRisk_level(String risk_level) {
    this.risk_level = risk_level;
}

public void setReason(String reason) {
    this.reason = reason;
}

public void setConfidence(Double confidence) {
    this.confidence = confidence;
}

public void setPrompt_tokens(Integer prompt_tokens) {
    this.prompt_tokens = prompt_tokens;
}

public void setCompletion_tokens(Integer completion_tokens) {
    this.completion_tokens = completion_tokens;
}

public void setTotal_tokens(Integer total_tokens) {
    this.total_tokens = total_tokens;
}

public void setLatency_ms(Double latency_ms) {
    this.latency_ms = latency_ms;
}
}