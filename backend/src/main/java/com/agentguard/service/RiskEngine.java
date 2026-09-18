package com.agentguard.service;

import com.agentguard.model.RiskLevel;
import com.agentguard.model.ToolAction;
import com.agentguard.model.ToolType;
import org.springframework.stereotype.Service;

@Service
public class RiskEngine {

    public RiskLevel assessRisk(ToolType tool, ToolAction action) {

        if (tool == ToolType.SHELL && action == ToolAction.EXECUTE) {
            return RiskLevel.CRITICAL;
        }

        if (tool == ToolType.FILESYSTEM
                && action == ToolAction.DELETE) {
            return RiskLevel.HIGH;
        }

        if (tool == ToolType.FILESYSTEM
                && action == ToolAction.WRITE) {
            return RiskLevel.MEDIUM;
        }

        if (tool == ToolType.DATABASE
                && action == ToolAction.QUERY) {
            return RiskLevel.MEDIUM;
        }

        if (tool == ToolType.GIT
                && action == ToolAction.PUSH) {
            return RiskLevel.HIGH;
        }

        if (tool == ToolType.HTTP
                && action == ToolAction.EXECUTE) {
            return RiskLevel.HIGH;
        }

        return RiskLevel.LOW;
    }
}