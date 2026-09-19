"use client";

import { useEffect, useState } from "react";

interface AuditLog {
  id: number;
  tool: string;
  action: string;
  decision: string;
  riskLevel: string;
  aiRiskLevel?: string;
  aiRiskReason?: string;
  aiConfidence?: number;
  createdAt: string;
  agent?: {
    name: string;
  };
}

export default function AuditLogsPage() {
  const [logs, setLogs] = useState<AuditLog[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchLogs = async () => {
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
          throw new Error("Failed to fetch audit logs");
        }

        const data = await response.json();
        setLogs(data);
      } catch (error) {
        console.error("Error fetching audit logs:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchLogs();
  }, []);

  return (
    <main className="min-h-screen bg-slate-950 text-white">
      <div className="flex min-h-screen">

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
              className="block rounded-lg px-4 py-3 text-sm text-slate-400 hover:bg-slate-800"
            >
              Dashboard
            </a>

            <a
              href="/agents"
              className="block rounded-lg px-4 py-3 text-sm text-slate-400 hover:bg-slate-800"
            >
              Agents
            </a>

            <a
              href="/tool-calls"
              className="block rounded-lg px-4 py-3 text-sm text-slate-400 hover:bg-slate-800"
            >
              Tool Calls
            </a>

            <a
              href="/policies"
              className="block rounded-lg px-4 py-3 text-sm text-slate-400 hover:bg-slate-800"
            >
              Policies
            </a>

            <a
              href="/audit-logs"
              className="block rounded-lg bg-slate-800 px-4 py-3 text-sm font-medium"
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

        <section className="flex-1 p-8">

          <div className="mb-8">
            <h2 className="text-3xl font-bold">
              Audit Logs
            </h2>

            <p className="mt-2 text-slate-400">
              Centralized record of AI agent security decisions and activity.
            </p>
          </div>

          {loading ? (
            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6 text-slate-400">
              Loading audit logs...
            </div>
          ) : (
            <div className="overflow-hidden rounded-xl border border-slate-800 bg-slate-900">
              <div className="overflow-x-auto">
                <table className="w-full text-left text-sm">

                  <thead className="border-b border-slate-800 bg-slate-950/50">
                    <tr>
                      <th className="px-5 py-4">Time</th>
                      <th className="px-5 py-4">Agent</th>
                      <th className="px-5 py-4">Tool</th>
                      <th className="px-5 py-4">Action</th>
                      <th className="px-5 py-4">Decision</th>
                      <th className="px-5 py-4">Risk</th>
                      <th className="px-5 py-4">AI Risk</th>
                    </tr>
                  </thead>

                  <tbody>
                    {logs
                      .slice()
                      .reverse()
                      .map((log) => (
                        <tr
                          key={log.id}
                          className="border-b border-slate-800 last:border-b-0 hover:bg-slate-800/40"
                        >
                          <td className="px-5 py-4 text-slate-400">
                            {new Date(log.createdAt).toLocaleString()}
                          </td>

                          <td className="px-5 py-4">
                            {log.agent?.name ?? "Unknown"}
                          </td>

                          <td className="px-5 py-4 font-medium">
                            {log.tool}
                          </td>

                          <td className="px-5 py-4">
                            {log.action}
                          </td>

                          <td className="px-5 py-4">
                            <span
                              className={`rounded-full px-3 py-1 text-xs ${
                                log.decision === "BLOCK"
                                  ? "bg-red-500/10 text-red-400"
                                  : log.decision === "REQUIRE_APPROVAL"
                                  ? "bg-yellow-500/10 text-yellow-400"
                                  : "bg-green-500/10 text-green-400"
                              }`}
                            >
                              {log.decision}
                            </span>
                          </td>

                          <td className="px-5 py-4">
                            {log.riskLevel}
                          </td>

                          <td className="px-5 py-4">
                            {log.aiRiskLevel ?? "N/A"}
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