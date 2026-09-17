package com.agentguard.model;

import jakarta.persistence.*;

@Entity
@Table(name = "policies")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToolType tool;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ToolAction action;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PolicyDecision decision;

    @Column(nullable = false)
    private boolean enabled = true;

    public Policy() {
    }

    public Policy(String name,
                  String description,
                  ToolType tool,
                  ToolAction action,
                  PolicyDecision decision,
                  boolean enabled) {
        this.name = name;
        this.description = description;
        this.tool = tool;
        this.action = action;
        this.decision = decision;
        this.enabled = enabled;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public PolicyDecision getDecision() {
        return decision;
    }

    public void setDecision(PolicyDecision decision) {
        this.decision = decision;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Generate getters and setters
    
}
