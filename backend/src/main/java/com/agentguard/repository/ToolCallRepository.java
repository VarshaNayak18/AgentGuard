package com.agentguard.repository;

import com.agentguard.model.PolicyDecision;
import com.agentguard.model.RiskLevel;
import com.agentguard.model.ToolCall;
import com.agentguard.model.ToolType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolCallRepository extends JpaRepository<ToolCall, Long> {

    List<ToolCall> findByDecision(PolicyDecision decision);
    
    List<ToolCall> findByTool(ToolType tool);
    
    List<ToolCall> findByAgentId(Long agentId);

    List<ToolCall> findByRiskLevel(RiskLevel riskLevel);
}