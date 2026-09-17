package com.agentguard.controller;

import com.agentguard.dto.AgentRegistrationRequest;
import com.agentguard.model.Agent;
import com.agentguard.service.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping
    public ResponseEntity<Agent> registerAgent(
            @RequestBody AgentRegistrationRequest request) {

        Agent agent = agentService.registerAgent(request);

        return ResponseEntity.ok(agent);
    }
}