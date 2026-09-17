package com.agentguard.dto;

import com.agentguard.model.AgentType;
import com.agentguard.model.Environment;

public class AgentRegistrationRequest {

    private String name;
    private AgentType type;
    private Environment environment;

    public AgentRegistrationRequest() {
    }

    public AgentRegistrationRequest(String name,
                                    AgentType type,
                                    Environment environment) {
        this.name = name;
        this.type = type;
        this.environment = environment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AgentType getType() {
        return type;
    }

    public void setType(AgentType type) {
        this.type = type;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}