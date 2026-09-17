package com.agentguard.service;

import com.agentguard.model.Policy;
import com.agentguard.model.PolicyDecision;

public class PolicyEvaluationResult {

    private final PolicyDecision decision;
    private final Policy policy;

    public PolicyEvaluationResult(
            PolicyDecision decision,
            Policy policy) {

        this.decision = decision;
        this.policy = policy;
    }

    public PolicyDecision getDecision() {
        return decision;
    }

    public Policy getPolicy() {
        return policy;
    }
}