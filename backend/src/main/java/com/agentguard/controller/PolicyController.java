package com.agentguard.controller;

import com.agentguard.dto.PolicyRequest;
import com.agentguard.model.Policy;
import com.agentguard.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    public ResponseEntity<Policy> createPolicy(
            @RequestBody PolicyRequest request) {

        Policy policy = policyService.createPolicy(request);

        return ResponseEntity.ok(policy);
    }

    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {

        return ResponseEntity.ok(policyService.getAllPolicies());
    }
}