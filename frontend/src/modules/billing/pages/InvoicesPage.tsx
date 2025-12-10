import { useEffect, useState } from "react";
import InvoiceTable from "../components/InvoiceTable";
import { Invoice } from "../types/invoiceTypes";
import { invoiceService } from "../services/invoiceService";

export default function InvoicesPage() {
  const [invoices, setInvoices] = useState<Invoice[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    invoiceService.getAll()
      .then(setInvoices)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div>Caricamento...</div>;
  if (error) return <div className="text-red-500">{error}</div>;

  return (
    <div className="p-6">
      <div className="mb-6 flex items-center justify-between">
        <h1 className="text-2xl font-semibold">Fatture</h1>
        <button className="px-4 py-2 rounded bg-green-600 text-white hover:bg-green-700">
          + Nuova fattura
        </button>
      </div>
      <InvoiceTable invoices={invoices} />
    </div>
  );
}