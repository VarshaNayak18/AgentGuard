package com.agentguard.controller;

import com.agentguard.dto.ToolCallRequest;
import com.agentguard.model.ToolCall;
import com.agentguard.service.ToolCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tool-calls")
public class ToolCallController {

    private final ToolCallService toolCallService;

    public ToolCallController(ToolCallService toolCallService) {
        this.toolCallService = toolCallService;
    }

    @PostMapping
    public ResponseEntity<ToolCall> createToolCall(
            @RequestBody ToolCallRequest request) {

        ToolCall toolCall = toolCallService.createToolCall(request);

        return ResponseEntity.ok(toolCall);
    }
}