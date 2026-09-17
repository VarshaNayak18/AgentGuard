package com.agentguard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tool_calls")
public class ToolCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @Enumerated(EnumType.STRING)
    private ToolType tool;

    @Enumerated(EnumType.STRING)
    private ToolAction action;

    @Column(columnDefinition = "TEXT")
    private String parameters;

    private LocalDateTime createdAt;

    public ToolCall() {
    }

    public ToolCall(Agent agent,
                    ToolType tool,
                    ToolAction action,
                    String parameters,
                    LocalDateTime createdAt) {
        this.agent = agent;
        this.tool = tool;
        this.action = action;
        this.parameters = parameters;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public ToolType getTool() {
        return tool;
    }

    public void setTool(ToolType tool) {
        this.tool = tool;
    }

    public ToolAction getAction() {
        return action;
    }

    public void setAction(ToolAction action) {
        this.action = action;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // getters and setters
    
}