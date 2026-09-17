package com.agentguard.service;

import com.agentguard.model.Policy;
import com.agentguard.model.PolicyDecision;
import com.agentguard.model.ToolCall;
import com.agentguard.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyEngine {

    private final PolicyRepository policyRepository;

    public PolicyEngine(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public PolicyEvaluationResult evaluate(ToolCall toolCall) {

    List<Policy> policies =
            policyRepository.findByEnabledTrueOrderByPriorityDesc();

    for (Policy policy : policies) {

        if (policy.getTool() == toolCall.getTool()
                && policy.getAction() == toolCall.getAction()) {

            return new PolicyEvaluationResult(
                    policy.getDecision(),
                    policy
            );
        }
    }

    return new PolicyEvaluationResult(
            PolicyDecision.ALLOW,
            null
    );
}
}