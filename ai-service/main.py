from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI(
    title="AgentGuard AI Security Analyzer",
    version="1.0.0"
)


class ToolCallRequest(BaseModel):
    tool: str
    action: str
    parameters: str


class SecurityAnalysisResponse(BaseModel):
    risk_level: str
    reason: str


@app.get("/health")
def health():
    return {
        "status": "AI Security Analyzer is running"
    }


@app.post("/analyze", response_model=SecurityAnalysisResponse)
def analyze_tool_call(request: ToolCallRequest):

    tool = request.tool.upper()
    action = request.action.upper()
    parameters = request.parameters.lower()

    # Critical shell patterns
    if tool == "SHELL" and action == "EXECUTE":

        dangerous_patterns = [
            "curl",
            "wget",
            "powershell",
            "rm -rf",
            "chmod +x",
            "| bash",
            "| sh"
        ]

        for pattern in dangerous_patterns:
            if pattern in parameters:
                return SecurityAnalysisResponse(
                    risk_level="CRITICAL",
                    reason=(
                        f"Shell command contains potentially dangerous "
                        f"pattern: {pattern}"
                    )
                )

        return SecurityAnalysisResponse(
            risk_level="MEDIUM",
            reason="Shell command requires contextual security review."
        )

    # File deletion
    if tool == "FILESYSTEM" and action == "DELETE":
        return SecurityAnalysisResponse(
            risk_level="HIGH",
            reason="File deletion is a destructive filesystem operation."
        )

    # File writing
    if tool == "FILESYSTEM" and action == "WRITE":

        sensitive_paths = [
            ".env",
            "application.properties",
            "id_rsa",
            "credentials"
        ]

        for path in sensitive_paths:
            if path in parameters:
                return SecurityAnalysisResponse(
                    risk_level="HIGH",
                    reason=(
                        f"Write operation targets potentially sensitive "
                        f"resource: {path}"
                    )
                )

        return SecurityAnalysisResponse(
            risk_level="MEDIUM",
            reason="File write operation can modify local resources."
        )

    # Git push
    if tool == "GIT" and action == "PUSH":
        return SecurityAnalysisResponse(
            risk_level="HIGH",
            reason="Git push can publish changes to a remote repository."
        )

    # Database operations
    if tool == "DATABASE" and action == "QUERY":

        sensitive_keywords = [
            "password",
            "secret",
            "token",
            "api_key"
        ]

        for keyword in sensitive_keywords:
            if keyword in parameters:
                return SecurityAnalysisResponse(
                    risk_level="HIGH",
                    reason=(
                        f"Database query references potentially sensitive "
                        f"data: {keyword}"
                    )
                )

        return SecurityAnalysisResponse(
            risk_level="MEDIUM",
            reason="Database query requires data-access review."
        )

    # Default
    return SecurityAnalysisResponse(
        risk_level="LOW",
        reason="No high-risk contextual pattern detected."
    )