package com.agentguard.dto;

public class SecurityAnalysisResponse {

    private String risk_level;
    private String reason;

    public String getRisk_level() {
        return risk_level;
    }

    public String getReason() {
        return reason;
    }
}