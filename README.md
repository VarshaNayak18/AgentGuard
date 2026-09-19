# 🛡️ AgentGuard — AI Agent Governance & Runtime Security Platform

AgentGuard is a security and governance platform for monitoring and controlling AI-agent tool usage at runtime.

It intercepts AI-agent tool calls, evaluates them against configurable security policies, performs AI-assisted risk analysis, records runtime decisions, and provides a centralized dashboard for security monitoring and auditing.

---

## 🚀 Problem

Autonomous AI agents increasingly interact with powerful tools such as:

- Shell commands
- File systems
- Git repositories
- Databases
- HTTP services

A malicious, unsafe, or incorrectly generated tool call can potentially perform destructive or unauthorized operations.

Traditional applications often lack visibility into:

- Which AI agent performed an action
- Which tool was invoked
- What action was requested
- Whether the action violated a policy
- How risky the action was
- What the AI security analyzer concluded
- How much an LLM-based analysis cost in tokens and latency

AgentGuard addresses this problem by introducing a centralized runtime governance layer between AI agents and their tool execution environment.

---

## 💡 Solution

AgentGuard provides a runtime security pipeline:

```text
                    AI Agent
                       │
                       ▼
              ┌─────────────────┐
              │    AgentGuard   │
              │    Gateway      │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │  Policy Engine  │
              │                 │
              │ Priority-based  │
              │ policy matching │
              └────────┬────────┘
                       │
              ┌────────┴─────────┐
              │                  │
            ALLOW              BLOCK
              │                  │
              └────────┬─────────┘
                       │
                       ▼
              ┌─────────────────┐
              │   Risk Engine   │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │ AI Security     │
              │ Analyzer        │
              │                 │
              │ Python/FastAPI  │
              │ + LLM           │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │ Audit & Metrics │
              │                 │
              │ PostgreSQL      │
              └────────┬────────┘
                       │
                       ▼
              ┌─────────────────┐
              │ Security        │
              │ Dashboard       │
              │ Next.js         │
              └─────────────────┘
```

## ✨ Key Features

### 🔐 Authentication & Authorization

- JWT-based authentication
- Role-based access control
- Protected backend APIs
- Admin-only agent and policy management

### 🤖 AI Agent Management

Register and monitor AI agents with:

- Agent name
- Agent type
- Environment
- Status

Supported agent types include:

- Coding Agent
- Testing Agent
- Code Review Agent

Supported environments:

- Development
- Staging
- Production

### 🛡️ Policy Engine

AgentGuard evaluates tool calls against configurable security policies.

Policies can define:

- Tool type
- Tool action
- Decision
- Priority
- Enabled/disabled state

Supported decisions:

```text
ALLOW
BLOCK
REQUIRE_APPROVAL
```

Policies are evaluated using priority ordering, allowing higher-priority policies to take precedence when multiple policies match a tool call.

### ⚠️ Risk Analysis

Tool calls are assigned security risk levels:

**LOW · MEDIUM · HIGH · CRITICAL**

The platform provides:

- Risk classification
- Risk filtering
- Risk statistics
- High-risk activity monitoring

### 🧠 LLM-Powered Security Analysis

AgentGuard integrates a Python FastAPI security-analysis service.

The service sends tool-call context to an LLM and returns structured security analysis containing:

- Risk level
- Security reasoning
- Confidence score

Structured LLM output is used to keep the response predictable and machine-readable.

### 📊 LLM Usage & Performance Telemetry

AgentGuard records LLM analysis metrics including:

- Prompt tokens
- Completion tokens
- Total tokens
- Analysis latency
- Confidence score

This provides visibility into the cost and performance characteristics of AI-assisted security analysis.

---

### 🛡️ Resilient AI Analysis

The Java backend includes a fallback mechanism for situations where the AI security-analysis service is unavailable.

Instead of completely failing a tool-call request, AgentGuard records a deterministic fallback assessment.

This allows the governance pipeline to remain operational even when the external AI-analysis service is unavailable.

---

### 📝 Centralized Audit Logging

Every tracked tool call can contain:

- Agent
- Tool
- Action
- Parameters
- Policy decision
- Risk level
- AI risk assessment
- AI reasoning
- Confidence
- LLM token usage
- LLM latency
- Timestamp

The dashboard provides a dedicated Audit Logs view for reviewing this activity.

---

## 🖥️ Dashboard

AgentGuard includes a Next.js dashboard for monitoring the security state of the platform.

Displays:

- Active agents
- Total tool calls
- Blocked actions
- High-risk events
- Risk distribution
- Recent security activity

### Agents

View registered AI agents and their:

- Type
- Environment
- Status

### Tool Calls

Review tracked tool calls and their runtime decisions.

### Policies

View configured security policies and their priorities.

### Audit Logs

Review historical tool activity and AI security analysis results.

---

## 🧰 Supported Tool Types

AgentGuard currently models the following tool categories:

**SHELL · FILESYSTEM · GIT · HTTP · DATABASE**

Supported actions include:

**READ · WRITE · EXECUTE · DELETE · PUSH · QUERY**

---

## 🏗️ Architecture

AgentGuard is implemented as a multi-component system:

```text
┌─────────────────────────────────────────────┐
│                Next.js UI                   │
│                                             │
│ Dashboard | Agents | Policies | Audit Logs │
└──────────────────────┬──────────────────────┘
                       │ REST API
                       ▼
┌─────────────────────────────────────────────┐
│             Spring Boot Backend             │
│                                             │
│ Authentication                              │
│ Agent Management                            │
│ Tool Call Tracking                          │
│ Policy Engine                               │
│ Risk Analysis                               │
│ Audit Logging                               │
└──────────────┬──────────────────┬───────────┘
               │                  │
               ▼                  ▼
        ┌─────────────┐    ┌─────────────────┐
        │ PostgreSQL  │    │ Python FastAPI  │
        │             │    │ AI Analyzer     │
        └─────────────┘    └────────┬────────┘
                                    │
                                    ▼
                              ┌─────────────┐
                              │     LLM     │
                              └─────────────┘

                     Redis
                       │
                       ▼
                Runtime Infrastructure
```

---

## 🛠️ Tech Stack

### Backend

- Java
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- REST APIs
- Maven

### AI Security Service

- Python
- FastAPI
- Pydantic
- Groq API
- LLM structured output

### Frontend

- Next.js
- React
- TypeScript
- Tailwind CSS

### Data & Infrastructure

- PostgreSQL
- Redis
- Docker
- Docker Compose

---

## 📁 Project Structure

```text
AgentGuard/
│
├── backend/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── agentguard/
│   │
│   ├── Dockerfile
│   ├── pom.xml
│   └── .gitignore
│
├── ai-service/
│   ├── main.py
│   ├── requirements.txt
│   └── venv/
│
├── frontend/
│   ├── app/
│   │   ├── agents/
│   │   ├── audit-logs/
│   │   ├── login/
│   │   ├── policies/
│   │   └── tool-calls/
│   ├── package.json
│   └── tsconfig.json
│
├── docker-compose.yml
├── .env.example
└── README.md
```

---

## 🐳 Running with Docker

### Prerequisites

Install:

- Docker Desktop
- Git

Clone the repository:

```bash
git clone https://github.com/VarshaNayak18/AgentGuard
cd AgentGuard
```

### Configure Environment Variables

Create a local `.env` file in the project root.

Example:

```env
POSTGRES_PASSWORD=your_postgres_password
JWT_SECRET=your_jwt_secret
AGENTGUARD_ADMIN_USERNAME=admin
AGENTGUARD_ADMIN_PASSWORD=your_admin_password
```

Never commit .env to Git. Use .env.example as the template.

### Start the Backend Infrastructure

Run:
    ```bash
    docker compose up -d
    ```

Check the running containers:
    ```bash
    docker compose ps
    ```

Expected services:
    ```bash
    agentguard-backend
    agentguard-postgres
    agentguard-redis
    ```

### Backend Health Check

Once the containers are running:

    http://localhost:8080/api/health

Expected response:

    AgentGuard is running!

---

## 💻 Running the Frontend

Navigate to the frontend:

    ``bash
    cd frontend
    ```

Install dependencies:

    ``bash
    npm install
    ```

Start the development server using Webpack:

    ``bash
    npm run dev -- --webpack
    ```

Open:

    http://localhost:3000

---

## 🔑 Authentication

The application uses JWT-based authentication.

The frontend stores the authentication token after login and sends it with protected API requests:

    Authorization: Bearer <JWT_TOKEN>

The backend validates the token through Spring Security before allowing access to protected endpoints.

---

## 🔌 Core API Endpoints

### Authentication

    POST /api/auth/login

### Agents

    POST /api/agents
    GET  /api/agents

### Tool Calls

    POST /api/tool-calls
    GET  /api/tool-calls
    GET  /api/tool-calls/decision/{decision}
    GET  /api/tool-calls/tool/{tool}
    GET  /api/tool-calls/agent/{agentId}
    GET  /api/tool-calls/risk/{riskLevel}
    GET  /api/tool-calls/stats/risk

### Policies

    POST /api/policies
    GET  /api/policies

### Health

    GET /api/health

---

## 🔄 Example Runtime Flow

A typical tool call follows this conceptual flow:

    1. AI agent requests a tool action
                  ↓
    2. AgentGuard receives the request
                  ↓
    3. Policy Engine evaluates matching policies
                  ↓
    4. Policy decision is determined
                  ↓
    5. Security risk is evaluated
                  ↓
    6. AI Security Analyzer performs contextual analysis
                  ↓
    7. LLM risk assessment is returned
                  ↓
    8. Tool-call record is persisted
                  ↓
    9. Dashboard exposes the activity

---

## 🧪 Example Security Scenario

Consider an AI coding agent requesting:

    Tool: FILESYSTEM
    Action: DELETE
    Parameters: /project/database.sql

AgentGuard can evaluate this request against a policy such as:

    Policy:
    Block File Deletion

    Tool:
    FILESYSTEM

    Action:
    DELETE

    Decision:
    BLOCK

    Priority:
    100

The resulting tool-call record can then be audited through the dashboard.

---

## 🔒 Security Design

AgentGuard uses multiple layers of security:

    JWT Authentication
            ↓
    Role-Based Authorization
            ↓
    Policy Evaluation
            ↓
    Risk Classification
            ↓
    AI Security Analysis
            ↓
    Audit Logging

This layered approach provides both deterministic policy enforcement and contextual AI-assisted analysis.

---

## 📈 Observability

AgentGuard tracks security and AI-analysis telemetry such as:

- Total tool calls
- Blocked actions
- Risk distribution
- Agent activity
- Policy decisions
- AI risk levels
- AI confidence
- Prompt tokens
- Completion tokens
- Total tokens
- LLM latency

This allows the platform to provide visibility into both runtime security events and AI-analysis performance.

---

## 🔮 Future Improvements

Potential future extensions include:

- Model Context Protocol (MCP) integration
- Kubernetes-native deployment
- Distributed policy management
- Human approval workflows
- Advanced anomaly detection
- Multiple LLM provider support
- Real-time WebSocket security monitoring
- Policy simulation and testing
- OpenTelemetry-based observability
- Fine-grained agent permissions
- Production-grade secrets management

---

## 🎯 Project Goals

AgentGuard was built to explore the intersection of:

- AI agent security
- Runtime governance
- Application security
- Policy-based authorization
- LLM security analysis
- Distributed systems
- Observability
- Full-stack engineering

The project demonstrates how deterministic security policies can be combined with AI-assisted contextual analysis to provide greater visibility and control over AI-agent activity.

---

## 👩‍💻 Author

**Varsha V Nayak**

Bachelor of Engineering — Robotics and Artificial Intelligence

Bangalore Institute of Technology

---

## ⭐ Project Highlights

    AI Agent Governance
            +
    Runtime Security
            +
    Policy Engine
            +
    LLM Security Analysis
            +
    Audit Logging
            +
    Security Dashboard
            +
    Dockerized Infrastructure

Built with Java, Spring Boot, Python, FastAPI, Next.js, PostgreSQL, Redis, Docker, and LLM APIs.