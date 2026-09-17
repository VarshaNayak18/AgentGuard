package com.agentguard.service;

import com.agentguard.dto.PolicyRequest;
import com.agentguard.model.Policy;
import com.agentguard.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public Policy createPolicy(PolicyRequest request) {

        Policy policy = new Policy();

        policy.setName(request.getName());
        policy.setDescription(request.getDescription());
        policy.setTool(request.getTool());
        policy.setAction(request.getAction());
        policy.setDecision(request.getDecision());
        policy.setEnabled(true);

        return policyRepository.save(policy);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}