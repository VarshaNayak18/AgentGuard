package com.agentguard.controller;

import com.agentguard.dto.PolicyRequest;
import com.agentguard.model.Policy;
import com.agentguard.service.PolicyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Policy> createPolicy(
            @RequestBody PolicyRequest request) {

        Policy policy = policyService.createPolicy(request);

        return ResponseEntity.ok(policy);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {

        return ResponseEntity.ok(policyService.getAllPolicies());
    }
}