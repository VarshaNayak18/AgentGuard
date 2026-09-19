"use client";

import { useEffect, useState } from "react";

interface ToolCall {
  id: number;
  tool: string;
  action: string;
  parameters: string;
  decision: string;
  riskLevel: string;
  createdAt: string;
  agent?: {
    name: string;
  };
}

export default function ToolCallsPage() {
  const [toolCalls, setToolCalls] = useState<ToolCall[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
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

        const data = await response.json();
        setToolCalls(data);
      } catch (error) {
        console.error("Error fetching tool calls:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchToolCalls();
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
            <a
              href="/"
              className="block w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800"
            >
              Dashboard
            </a>

            <button className="w-full rounded-lg bg-slate-800 px-4 py-3 text-left text-sm font-medium">
              Tool Calls
            </button>

            <button className="w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800">
              Agents
            </button>

            <button className="w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800">
              Policies
            </button>

            <button className="w-full rounded-lg px-4 py-3 text-left text-sm text-slate-400 hover:bg-slate-800">
              Audit Logs
            </button>

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

        {/* Main content */}
        <section className="flex-1 p-8">

          <div className="mb-8">
            <h2 className="text-3xl font-bold">
              Tool Calls
            </h2>

            <p className="mt-2 text-slate-400">
              Monitor and inspect AI agent tool activity.
            </p>
          </div>

          {loading ? (
            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6 text-slate-400">
              Loading tool calls...
            </div>
          ) : toolCalls.length === 0 ? (
            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6 text-slate-400">
              No tool calls recorded.
            </div>
          ) : (
            <div className="overflow-hidden rounded-xl border border-slate-800 bg-slate-900">

              <div className="overflow-x-auto">
                <table className="w-full text-left text-sm">

                  <thead className="border-b border-slate-800 bg-slate-950/50">
                    <tr>
                      <th className="px-5 py-4">ID</th>
                      <th className="px-5 py-4">Agent</th>
                      <th className="px-5 py-4">Tool</th>
                      <th className="px-5 py-4">Action</th>
                      <th className="px-5 py-4">Decision</th>
                      <th className="px-5 py-4">Risk</th>
                      <th className="px-5 py-4">Created</th>
                    </tr>
                  </thead>

                  <tbody>
                    {toolCalls
                      .slice()
                      .reverse()
                      .map((call) => (
                        <tr
                          key={call.id}
                          className="border-b border-slate-800 last:border-b-0 hover:bg-slate-800/40"
                        >
                          <td className="px-5 py-4 text-slate-400">
                            #{call.id}
                          </td>

                          <td className="px-5 py-4">
                            {call.agent?.name ?? "Unknown"}
                          </td>

                          <td className="px-5 py-4 font-medium">
                            {call.tool}
                          </td>

                          <td className="px-5 py-4">
                            {call.action}
                          </td>

                          <td className="px-5 py-4">
                            <span
                              className={`rounded-full px-3 py-1 text-xs ${
                                call.decision === "BLOCK"
                                  ? "bg-red-500/10 text-red-400"
                                  : call.decision === "REQUIRE_APPROVAL"
                                  ? "bg-yellow-500/10 text-yellow-400"
                                  : "bg-green-500/10 text-green-400"
                              }`}
                            >
                              {call.decision}
                            </span>
                          </td>

                          <td className="px-5 py-4">
                            <span
                              className={`rounded-full px-3 py-1 text-xs ${
                                call.riskLevel === "CRITICAL"
                                  ? "bg-red-500/10 text-red-400"
                                  : call.riskLevel === "HIGH"
                                  ? "bg-orange-500/10 text-orange-400"
                                  : call.riskLevel === "MEDIUM"
                                  ? "bg-yellow-500/10 text-yellow-400"
                                  : "bg-green-500/10 text-green-400"
                              }`}
                            >
                              {call.riskLevel}
                            </span>
                          </td>

                          <td className="px-5 py-4 text-slate-400">
                            {new Date(call.createdAt).toLocaleString()}
                          </td>
                        </tr>
                      ))}
                  </tbody>

                </table>
              </div>
            </div>
          )}

        </section>
      </div>
    </main>
  );
}