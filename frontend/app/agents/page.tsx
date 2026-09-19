"use client";

import { useEffect, useState } from "react";

interface Agent {
  id: number;
  name: string;
  type: string;
  environment: string;
  status: string;
}

export default function AgentsPage() {
  const [agents, setAgents] = useState<Agent[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
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

        const data = await response.json();
        setAgents(data);
      } catch (error) {
        console.error("Error fetching agents:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchAgents();
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
              className="block rounded-lg bg-slate-800 px-4 py-3 text-sm font-medium"
            >
              Agents
            </a>

            <a
              href="/tool-calls"
              className="block rounded-lg px-4 py-3 text-sm text-slate-400 hover:bg-slate-800"
            >
              Tool Calls
            </a>

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

        <section className="flex-1 p-8">
          <div className="mb-8">
            <h2 className="text-3xl font-bold">Agents</h2>
            <p className="mt-2 text-slate-400">
              Registered AI agents monitored by AgentGuard.
            </p>
          </div>

          {loading ? (
            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6 text-slate-400">
              Loading agents...
            </div>
          ) : (
            <div className="overflow-hidden rounded-xl border border-slate-800 bg-slate-900">
              <div className="overflow-x-auto">
                <table className="w-full text-left text-sm">
                  <thead className="border-b border-slate-800 bg-slate-950/50">
                    <tr>
                      <th className="px-5 py-4">ID</th>
                      <th className="px-5 py-4">Name</th>
                      <th className="px-5 py-4">Type</th>
                      <th className="px-5 py-4">Environment</th>
                      <th className="px-5 py-4">Status</th>
                    </tr>
                  </thead>

                  <tbody>
                    {agents.map((agent) => (
                      <tr
                        key={agent.id}
                        className="border-b border-slate-800 last:border-b-0 hover:bg-slate-800/40"
                      >
                        <td className="px-5 py-4 text-slate-400">
                          #{agent.id}
                        </td>

                        <td className="px-5 py-4 font-medium">
                          {agent.name}
                        </td>

                        <td className="px-5 py-4">
                          {agent.type}
                        </td>

                        <td className="px-5 py-4">
                          {agent.environment}
                        </td>

                        <td className="px-5 py-4">
                          <span
                            className={`rounded-full px-3 py-1 text-xs ${
                              agent.status === "ACTIVE"
                                ? "bg-green-500/10 text-green-400"
                                : "bg-slate-500/10 text-slate-400"
                            }`}
                          >
                            {agent.status}
                          </span>
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