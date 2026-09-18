import os
import json

from fastapi import FastAPI
from pydantic import BaseModel
from groq import Groq


app = FastAPI(
    title="AgentGuard AI Security Analyzer",
    version="1.0.0"
)

client = Groq(
    api_key=os.environ.get("GROQ_API_KEY")
)


class ToolCallRequest(BaseModel):
    tool: str
    action: str
    parameters: str


class SecurityAnalysisResponse(BaseModel):
    risk_level: str
    reason: str
    confidence: float
    prompt_tokens: int
    completion_tokens: int
    total_tokens: int
    latency_ms: float


@app.get("/health")
def health():
    return {"status": "AI Security Analyzer is running"}


@app.post("/analyze", response_model=SecurityAnalysisResponse)
def analyze_tool_call(request: ToolCallRequest):

    prompt = f"""
Analyze the following AI agent tool call from a cybersecurity perspective.

Tool: {request.tool}
Action: {request.action}
Parameters: {request.parameters}

Assess the potential security risk of this action.

Risk levels:
- LOW: Routine operation with minimal security concern.
- MEDIUM: Operation requires some security review.
- HIGH: Potentially dangerous or sensitive operation.
- CRITICAL: Highly dangerous operation that could cause serious security impact.

Return your assessment using the required structured format.
"""

    response = client.chat.completions.create(
        model="openai/gpt-oss-20b",
        messages=[
            {
                "role": "system",
                "content": (
                    "You are AgentGuard, an AI agent security analyst. "
                    "Analyze tool calls for security risks. "
                    "Be conservative when evaluating potentially destructive, "
                    "credential-related, network, filesystem, or execution operations."
                )
            },
            {
                "role": "user",
                "content": prompt
            }
        ],
        response_format={
            "type": "json_schema",
            "json_schema": {
                "name": "security_analysis",
                "strict": True,
                "schema": {
                    "type": "object",
                    "properties": {
                        "risk_level": {
                            "type": "string",
                            "enum": [
                                "LOW",
                                "MEDIUM",
                                "HIGH",
                                "CRITICAL"
                            ]
                        },
                        "reason": {
                            "type": "string"
                        },
                        "confidence": {
                            "type": "number"
                        }
                    },
                    "required": [
                        "risk_level",
                        "reason",
                        "confidence"
                    ],
                    "additionalProperties": False
                }
            }
        }
    )

    print("LLM Usage:")
    print("Prompt tokens:", response.usage.prompt_tokens)
    print("Completion tokens:", response.usage.completion_tokens)
    print("Total tokens:", response.usage.total_tokens)

    result = json.loads(
            response.choices[0].message.content
        )
    return SecurityAnalysisResponse(
    **result,
    prompt_tokens=response.usage.prompt_tokens,
    completion_tokens=response.usage.completion_tokens,
    total_tokens=response.usage.total_tokens,
    latency_ms=response.usage.total_time * 1000
)



    

    