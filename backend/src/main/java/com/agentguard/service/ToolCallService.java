package com.agentguard.service;

import com.agentguard.dto.ToolCallRequest;
import com.agentguard.model.Agent;
import com.agentguard.model.ToolCall;
import com.agentguard.repository.AgentRepository;
import com.agentguard.repository.ToolCallRepository;
import org.springframework.stereotype.Service;
// import com.agentguard.service.PolicyEvaluationResult;

import java.time.LocalDateTime;

@Service
public class ToolCallService {

    private final ToolCallRepository toolCallRepository;
    private final AgentRepository agentRepository;
    private final PolicyEngine policyEngine;
    
    public ToolCallService(ToolCallRepository toolCallRepository,
                       AgentRepository agentRepository,
                       PolicyEngine policyEngine) {
        this.toolCallRepository = toolCallRepository;
        this.agentRepository = agentRepository;
        this.policyEngine = policyEngine;
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
        
        return toolCallRepository.save(toolCall);
    }
}