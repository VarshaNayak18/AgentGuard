package com.agentguard.service;

import com.agentguard.dto.AgentRegistrationRequest;
import com.agentguard.model.Agent;
import com.agentguard.model.AgentStatus;
import com.agentguard.repository.AgentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public Agent registerAgent(AgentRegistrationRequest request) {

        Agent agent = new Agent();

        agent.setName(request.getName());
        agent.setType(request.getType());
        agent.setEnvironment(request.getEnvironment());
        agent.setStatus(AgentStatus.ACTIVE);

        return agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }
}