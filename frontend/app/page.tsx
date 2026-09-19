"use client";

import { useEffect, useState } from "react";

export default function Home() {
  const [riskStats, setRiskStats] = useState({
    LOW: 0,
    MEDIUM: 0,
    HIGH: 0,
    CRITICAL: 0,
  });

  const [activeAgents, setActiveAgents] = useState(0);
  const [toolCallCount, setToolCallCount] = useState(0);
  const [blockedCount, setBlockedCount] = useState(0);
  const [recentToolCalls, setRecentToolCalls] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
     const loadDashboard = async () => {
    const fetchRiskStats = async () => {
      try {
        const token = localStorage.getItem("token");

        const response = await fetch(
          "http://localhost:8080/api/tool-calls/stats/risk",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch risk statistics");
        }

        const data = await response.json();
        setRiskStats(data);
      } catch (error) {
        console.error("Error fetching risk statistics:", error);
      }
    };

    fetchRiskStats();

    const fetchBlockedActions = async () => {
  try {
    const token = localStorage.getItem("token");

    const response = await fetch(
      "http://localhost:8080/api/tool-calls/decision/BLOCK",
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (!response.ok) {
      throw new Error("Failed to fetch blocked actions");
    }

    const blockedCalls = await response.json();

    setBlockedCount(blockedCalls.length);
  } catch (error) {
    console.error("Error fetching blocked actions:", error);
  }
};

fetchBlockedActions();

const fetchAgents = async () => {
  try {
    const token = localStorage.getItem("token");

    const response = await fetch(
      "http://localhost:8080/api/agents",
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (!response.ok) {
      throw new Error("Failed to fetch agents");
    }

    const agents = await response.json();

    const activeCount = agents.filter(
      (agent: { status: string }) => agent.status === "ACTIVE"
    ).length;

    setActiveAgents(activeCount);
  } catch (error) {
    console.error("Error fetching agents:", error);
  }
};

fetchAgents();

const fetchToolCalls = async () => {
  try {
    const token = localStorage.getItem("token");

    const response = await fetch(
      "http://localhost:8080/api/tool-calls",
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    );

    if (!response.ok) {
      throw new Error("Failed to fetch tool calls");
    }

    const toolCalls = await response.json();

    setToolCallCount(toolCalls.length);
    setRecentToolCalls(toolCalls);
  } catch (error) {
    console.error("Error fetching tool calls:", error);
  }
};

fetchToolCalls();

setLoading(false);
  };
loadDashboard();
  }, []);


  return (
    <main className="min-h-screen bg-slate-950 text-white">
      <div className="flex min-h-screen">

        {/* Sidebar */}
        <aside className="w-64 border-r border-slate-800 bg-slate-900 p-6">
          <div className="mb-10">
            <h1 className="text-2xl font-bold">AgentGuard</h1>
            <p className="mt-1 text-xs text-slate-400">
              AI Agent Security Platform
            </p>
          </div>

          <nav className="space-y-2">
            <button className="w-full rounded-lg bg-slate-800 px-4 py-3 text-left text-sm font-medium">
              Dashboard
            </button>

            <a
  href="/agents"
  className="block w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800"
>
  Agents
</a>

            <a
  href="/tool-calls"
  className="block w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800"
>
  Tool Calls
</a>

            <a
  href="/policies"
  className="block w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800"
>
  Policies
</a>

            <a
  href="/audit-logs"
  className="block w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800"
>
  Audit Logs
</a>

            <button
  onClick={() => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("role");
    window.location.href = "/login";
  }}
  className="mt-6 w-full rounded-lg px-4 py-3 text-left text-sm text-red-400 hover:bg-red-500/10"
>
  Logout
</button>

          </nav>
        </aside>

        {/* Main Content */}
        <section className="flex-1 p-8">

          {/* Header */}
          <div className="mb-8">
            <h2 className="text-3xl font-bold">
              Security Dashboard
            </h2>
            <p className="mt-2 text-slate-400">
              Monitor AI agent activity, policy decisions, and security risks.
            </p>
          </div>

          {/* Overview Cards */}
          <div className="grid grid-cols-1 gap-5 md:grid-cols-2 lg:grid-cols-4">

            <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
              <p className="text-sm text-slate-400">Active Agents</p>
              <p className="mt-3 text-3xl font-bold">{activeAgents}</p>
            </div>

            <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
              <p className="text-sm text-slate-400">Tool Calls</p>
              <p className="mt-3 text-3xl font-bold">{toolCallCount}</p>
            </div>

            <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
              <p className="text-sm text-slate-400">Blocked Actions</p>
              <p className="mt-3 text-3xl font-bold">{blockedCount}</p>
            </div>

            <div className="rounded-xl border border-slate-800 bg-slate-900 p-5">
              <p className="text-sm text-slate-400">High Risk Events</p>
              <p className="mt-3 text-3xl font-bold">{riskStats.HIGH + riskStats.CRITICAL}</p>
            </div>

          </div>

          {/* Security Overview */}
          <div className="mt-8 grid grid-cols-1 gap-6 lg:grid-cols-2">

            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6">
              <h3 className="text-lg font-semibold">
                Recent Security Activity
              </h3>

              <div className="mt-5 space-y-4">
  {recentToolCalls.length === 0 ? (
    <p className="text-sm text-slate-400">
      No tool calls recorded yet.
    </p>
  ) : (
    recentToolCalls.slice(-5).reverse().map((call) => (
      <div
        key={call.id}
        className="flex items-center justify-between border-b border-slate-800 pb-4"
      >
        <div>
          <p className="text-sm font-medium">
            {call.tool} {call.action}
          </p>

          <p className="text-xs text-slate-500">
            Agent: {call.agent?.name ?? "Unknown"}
          </p>
        </div>

        <div className="text-right">
          <span
            className={`rounded-full px-3 py-1 text-xs ${
              call.decision === "BLOCK"
                ? "bg-red-500/10 text-red-400"
                : call.riskLevel === "CRITICAL"
                ? "bg-red-500/10 text-red-400"
                : call.riskLevel === "HIGH"
                ? "bg-orange-500/10 text-orange-400"
                : call.decision === "ALLOW"
                ? "bg-green-500/10 text-green-400"
                : "bg-yellow-500/10 text-yellow-400"
            }`}
          >
            {call.decision}
          </span>

          <p className="mt-1 text-xs text-slate-500">
            {call.riskLevel}
          </p>
        </div>
      </div>
    ))
  )}
</div>
            </div>

            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6">
              <h3 className="text-lg font-semibold">
                Risk Distribution
              </h3>

              <div className="mt-6 space-y-5">

                <div>
                  <div className="mb-2 flex justify-between text-sm">
                    <span>Low</span>
                    <span className="text-slate-400">{riskStats.LOW}</span>
                  </div>
                  <div className="h-2 rounded-full bg-slate-800">
                    <div className="h-2 w-3/4 rounded-full bg-green-500" />
                  </div>
                </div>

                <div>
                  <div className="mb-2 flex justify-between text-sm">
                    <span>Medium</span>
                    <span className="text-slate-400">{riskStats.MEDIUM}</span>
                  </div>
                  <div className="h-2 rounded-full bg-slate-800">
                    <div className="h-2 w-1/2 rounded-full bg-yellow-500" />
                  </div>
                </div>

                <div>
                  <div className="mb-2 flex justify-between text-sm">
                    <span>High</span>
                    <span className="text-slate-400">{riskStats.HIGH}</span>
                  </div>
                  <div className="h-2 rounded-full bg-slate-800">
                    <div className="h-2 w-1/3 rounded-full bg-orange-500" />
                  </div>
                </div>

                <div>
                  <div className="mb-2 flex justify-between text-sm">
                    <span>Critical</span>
                    <span className="text-slate-400">{riskStats.CRITICAL}</span>
                  </div>
                  <div className="h-2 rounded-full bg-slate-800">
                    <div className="h-2 w-1/4 rounded-full bg-red-500" />
                  </div>
                </div>

              </div>
            </div>

          </div>

        </section>
      </div>
    </main>
  );
}
