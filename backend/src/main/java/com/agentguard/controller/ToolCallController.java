package com.agentguard.controller;

import com.agentguard.dto.ToolCallRequest;
import com.agentguard.model.ToolCall;
import com.agentguard.service.ToolCallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.agentguard.model.PolicyDecision;
import com.agentguard.model.ToolType;

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

    @GetMapping
    public ResponseEntity<List<ToolCall>> getAllToolCalls() {
        return ResponseEntity.ok(toolCallService.getAllToolCalls());
    }

    @GetMapping("/decision/{decision}")
    public ResponseEntity<List<ToolCall>> getByDecision(
            @PathVariable PolicyDecision decision) {
    
        return ResponseEntity.ok(
                toolCallService.getToolCallsByDecision(decision)
        );
    }
    
    @GetMapping("/tool/{tool}")
    public ResponseEntity<List<ToolCall>> getByTool(
            @PathVariable ToolType tool) {
    
        return ResponseEntity.ok(
                toolCallService.getToolCallsByTool(tool)
        );
    }
    
    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<ToolCall>> getByAgent(
            @PathVariable Long agentId) {
    
        return ResponseEntity.ok(
                toolCallService.getToolCallsByAgent(agentId)
        );
    }
}