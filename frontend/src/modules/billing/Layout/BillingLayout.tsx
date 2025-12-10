import { Link, Outlet } from "react-router-dom";

export default function BillingLayout() {
  return (
    <div className="flex h-screen">
      {/* Sidebar */}
      <aside className="w-64 bg-gray-900 text-white p-6">
        <h2 className="text-xl font-bold mb-8">
          Billing
        </h2>

        <nav className="flex flex-col gap-4">
          <Link
            to="/billing/contracts"
            className="hover:text-blue-400"
          >
            Contratti
          </Link>

          <Link
            to="/billing/invoices"
            className="hover:text-blue-400"
          >
            Fatture
          </Link>

          <Link
            to="/billing/payments"
            className="hover:text-blue-400"
          >
            Pagamenti
          </Link>
        </nav>
      </aside>

      {/* Content */}
      <main className="flex-1 bg-gray-50">
        <Outlet />
      </main>
    </div>
  );
}
