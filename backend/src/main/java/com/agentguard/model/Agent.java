package com.agentguard.model;

public class Agent {

    private Long id;
    private String name;
    private String type;
    private String environment;
    private String status;

    public Agent() {
    }

    public Agent(Long id, String name, String type, String environment, String status) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}