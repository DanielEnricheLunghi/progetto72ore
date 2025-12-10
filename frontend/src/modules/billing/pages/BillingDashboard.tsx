import { Link } from "react-router-dom";

export default function BillingDashboard() {
  return (
    <div className="p-8">
      <h1 className="text-2xl font-bold mb-6">
        Billing
      </h1>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <Link
          to="/billing/contracts"
          className="border rounded-lg p-6 hover:bg-gray-50 transition"
        >
          <h2 className="text-xl font-semibold">Contratti</h2>
          <p className="text-gray-600 mt-2">
            Gestione dei contratti
          </p>
        </Link>

        <Link
          to="/billing/invoices"
          className="border rounded-lg p-6 hover:bg-gray-50 transition"
        >
          <h2 className="text-xl font-semibold">Fatture</h2>
          <p className="text-gray-600 mt-2">
            Elenco fatture
          </p>
        </Link>

        <Link
          to="/billing/payments"
          className="border rounded-lg p-6 hover:bg-gray-50 transition"
        >
          <h2 className="text-xl font-semibold">Pagamenti</h2>
          <p className="text-gray-600 mt-2">
            Storico pagamenti
          </p>
        </Link>
      </div>
    </div>
  );
}
