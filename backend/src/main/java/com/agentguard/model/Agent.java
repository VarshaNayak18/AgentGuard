package com.agentguard.model;

public class Agent {

    private Long id;
    private String name;
    private AgentType type;
    private Environment environment;
    private AgentStatus status;

    public Agent() {
    }

    public Agent(Long id, String name, AgentType type, Environment environment, AgentStatus status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.environment = environment;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AgentStatus getStatus() {
        return status;
    }

    public void setStatus(AgentStatus status) {
        this.status = status;
    }
}