package com.agentguard.dto;

import com.agentguard.model.ToolAction;
import com.agentguard.model.ToolType;

public class ToolCallRequest {

    private Long agentId;
    private ToolType tool;
    private ToolAction action;
    private String parameters;

    public ToolCallRequest() {
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public ToolType getTool() {
        return tool;
    }

    public void setTool(ToolType tool) {
        this.tool = tool;
    }

    public ToolAction getAction() {
        return action;
    }

    public void setAction(ToolAction action) {
        this.action = action;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}