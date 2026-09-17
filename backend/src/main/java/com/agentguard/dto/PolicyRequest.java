package com.agentguard.dto;

import com.agentguard.model.PolicyDecision;
import com.agentguard.model.ToolAction;
import com.agentguard.model.ToolType;

public class PolicyRequest {

    private String name;
    private String description;
    private ToolType tool;
    private ToolAction action;
    private PolicyDecision decision;

    public PolicyRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public PolicyDecision getDecision() {
        return decision;
    }

    public void setDecision(PolicyDecision decision) {
        this.decision = decision;
    }
}