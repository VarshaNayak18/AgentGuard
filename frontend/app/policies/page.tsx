"use client";

import { useEffect, useState } from "react";

interface Policy {
  id: number;
  name: string;
  description: string;
  tool: string;
  action: string;
  decision: string;
  enabled: boolean;
  priority: number;
}

export default function PoliciesPage() {
  const [policies, setPolicies] = useState<Policy[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPolicies = async () => {
      try {
        const token = localStorage.getItem("token");

        const response = await fetch(
          "http://localhost:8080/api/policies",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch policies");
        }

        const data = await response.json();
        setPolicies(data);
      } catch (error) {
        console.error("Error fetching policies:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchPolicies();
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
              className="block rounded-lg bg-slate-800 px-4 py-3 text-sm font-medium"
            >
              Policies
            </a>

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
            <h2 className="text-3xl font-bold">Policies</h2>
            <p className="mt-2 text-slate-400">
              Security policies governing AI agent tool usage.
            </p>
          </div>

          {loading ? (
            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6 text-slate-400">
              Loading policies...
            </div>
          ) : policies.length === 0 ? (
            <div className="rounded-xl border border-slate-800 bg-slate-900 p-6 text-slate-400">
              No policies configured.
            </div>
          ) : (
            <div className="overflow-hidden rounded-xl border border-slate-800 bg-slate-900">
              <div className="overflow-x-auto">
                <table className="w-full text-left text-sm">

                  <thead className="border-b border-slate-800 bg-slate-950/50">
                    <tr>
                      <th className="px-5 py-4">Name</th>
                      <th className="px-5 py-4">Tool</th>
                      <th className="px-5 py-4">Action</th>
                      <th className="px-5 py-4">Decision</th>
                      <th className="px-5 py-4">Priority</th>
                      <th className="px-5 py-4">Status</th>
                    </tr>
                  </thead>

                  <tbody>
                    {policies
                      .slice()
                      .sort((a, b) => b.priority - a.priority)
                      .map((policy) => (
                        <tr
                          key={policy.id}
                          className="border-b border-slate-800 last:border-b-0 hover:bg-slate-800/40"
                        >
                          <td className="px-5 py-4">
                            <div className="font-medium">
                              {policy.name}
                            </div>

                            <div className="mt-1 text-xs text-slate-500">
                              {policy.description}
                            </div>
                          </td>

                          <td className="px-5 py-4">
                            {policy.tool}
                          </td>

                          <td className="px-5 py-4">
                            {policy.action}
                          </td>

                          <td className="px-5 py-4">
                            <span
                              className={`rounded-full px-3 py-1 text-xs ${
                                policy.decision === "BLOCK"
                                  ? "bg-red-500/10 text-red-400"
                                  : policy.decision === "REQUIRE_APPROVAL"
                                  ? "bg-yellow-500/10 text-yellow-400"
                                  : "bg-green-500/10 text-green-400"
                              }`}
                            >
                              {policy.decision}
                            </span>
                          </td>

                          <td className="px-5 py-4">
                            {policy.priority}
                          </td>

                          <td className="px-5 py-4">
                            <span
                              className={`rounded-full px-3 py-1 text-xs ${
                                policy.enabled
                                  ? "bg-green-500/10 text-green-400"
                                  : "bg-slate-500/10 text-slate-400"
                              }`}
                            >
                              {policy.enabled ? "ENABLED" : "DISABLED"}
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