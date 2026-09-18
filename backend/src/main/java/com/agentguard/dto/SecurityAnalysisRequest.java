package com.agentguard.dto;

public class SecurityAnalysisRequest {

    private String tool;
    private String action;
    private String parameters;

    public SecurityAnalysisRequest(
            String tool,
            String action,
            String parameters) {

        this.tool = tool;
        this.action = action;
        this.parameters = parameters;
    }

    public String getTool() {
        return tool;
    }

    public String getAction() {
        return action;
    }

    public String getParameters() {
        return parameters;
    }
}