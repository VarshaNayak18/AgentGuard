package com.agentguard.service;

import com.agentguard.dto.AgentRegistrationRequest;
import com.agentguard.model.Agent;
import com.agentguard.model.AgentStatus;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    public Agent registerAgent(AgentRegistrationRequest request) {

        Agent agent = new Agent();

        agent.setName(request.getName());
        agent.setType(request.getType());
        agent.setEnvironment(request.getEnvironment());
        agent.setStatus(AgentStatus.ACTIVE);

        return agent;
    }
}
