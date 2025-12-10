import { useEffect, useState } from "react";
import PaymentTable from "../components/PaymentTable";
import { Payment } from "../types/paymentTypes";
import { paymentService } from "../services/paymentService";

export default function PaymentsPage() {
  const [payments, setPayments] = useState<Payment[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    paymentService.getAll()
      .then(setPayments)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div>Caricamento...</div>;
  if (error) return <div className="text-red-500">{error}</div>;

  return (
    <div className="p-6">
      <div className="mb-6 flex items-center justify-between">
        <h1 className="text-2xl font-semibold">Pagamenti</h1>
        <button className="px-4 py-2 rounded bg-green-600 text-white hover:bg-green-700">
          + Nuovo pagamento
        </button>
      </div>
      <PaymentTable payments={payments} />
    </div>
  );
}