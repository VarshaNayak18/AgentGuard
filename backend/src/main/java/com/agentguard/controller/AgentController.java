package com.agentguard.controller;

import com.agentguard.dto.AgentRegistrationRequest;
import com.agentguard.model.Agent;
import com.agentguard.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Agent> registerAgent(
            @RequestBody AgentRegistrationRequest request) {

        Agent agent = agentService.registerAgent(request);

        return ResponseEntity.ok(agent);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Agent>> getAllAgents() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }
}