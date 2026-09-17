package com.agentguard.repository;

import com.agentguard.model.ToolCall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolCallRepository extends JpaRepository<ToolCall, Long> {
}