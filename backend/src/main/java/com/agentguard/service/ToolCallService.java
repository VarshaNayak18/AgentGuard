package com.agentguard.service;

import com.agentguard.dto.ToolCallRequest;
import com.agentguard.model.Agent;
import com.agentguard.model.PolicyDecision;
import com.agentguard.model.RiskLevel;
import com.agentguard.model.ToolCall;
import com.agentguard.model.ToolType;
import com.agentguard.repository.AgentRepository;
import com.agentguard.repository.ToolCallRepository;
import org.springframework.stereotype.Service;
import java.util.EnumMap;
import java.util.Map;
// import com.agentguard.service.PolicyEvaluationResult;
import java.util.List;

import java.time.LocalDateTime;

@Service
public class ToolCallService {

    private final ToolCallRepository toolCallRepository;
    private final AgentRepository agentRepository;
    private final PolicyEngine policyEngine;
    private final RiskEngine riskEngine;
    
    public ToolCallService(ToolCallRepository toolCallRepository,
                       AgentRepository agentRepository,
                       PolicyEngine policyEngine,
                       RiskEngine riskEngine) {
        this.toolCallRepository = toolCallRepository;
        this.agentRepository = agentRepository;
        this.policyEngine = policyEngine;
        this.riskEngine = riskEngine;
    }

    public ToolCall createToolCall(ToolCallRequest request) {
        
        Agent agent = agentRepository.findById(request.getAgentId())
            .orElseThrow(() ->
                    new RuntimeException("Agent not found"));
                    
        
        ToolCall toolCall = new ToolCall();
        
        toolCall.setAgent(agent);
        toolCall.setTool(request.getTool());
        toolCall.setAction(request.getAction());
        toolCall.setParameters(request.getParameters());
        toolCall.setCreatedAt(LocalDateTime.now());
        
        PolicyEvaluationResult result = policyEngine.evaluate(toolCall);
        
        toolCall.setDecision(result.getDecision());
        toolCall.setPolicy(result.getPolicy());

        RiskLevel riskLevel =
        riskEngine.assessRisk(
                toolCall.getTool(),
                toolCall.getAction()
        );
        
        toolCall.setRiskLevel(riskLevel);
        
        return toolCallRepository.save(toolCall);
    }

    public List<ToolCall> getAllToolCalls() {
        return toolCallRepository.findAll();
    }

    public List<ToolCall> getToolCallsByDecision(PolicyDecision decision) {
        return toolCallRepository.findByDecision(decision);
    }
    
    public List<ToolCall> getToolCallsByTool(ToolType tool) {
        return toolCallRepository.findByTool(tool);
    }
    
    public List<ToolCall> getToolCallsByAgent(Long agentId) {
        return toolCallRepository.findByAgentId(agentId);
    }

    public List<ToolCall> getToolCallsByRiskLevel(RiskLevel riskLevel) {
        return toolCallRepository.findByRiskLevel(riskLevel);
    }

    public Map<RiskLevel, Long> getRiskStatistics() {

    Map<RiskLevel, Long> statistics = new EnumMap<>(RiskLevel.class);

    for (RiskLevel level : RiskLevel.values()) {
        statistics.put(
                level,
                (long) toolCallRepository.findByRiskLevel(level).size()
        );
    }

    return statistics;
}
}