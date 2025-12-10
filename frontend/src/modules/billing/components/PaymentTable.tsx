import React from "react";
import { Payment } from "../types/paymentTypes";

interface Props {
  payments: Payment[];
}

export default function PaymentTable({ payments }: Props) {
  if (payments.length === 0) {
    return <div className="text-center text-gray-500 py-6">Nessun pagamento presente</div>;
  }

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full border border-gray-200 rounded-lg">
        <thead className="bg-gray-100">
          <tr>
            <th className="px-4 py-2 text-left">Importo</th>
            <th className="px-4 py-2 text-left">Metodo</th>
            <th className="px-4 py-2 text-left">Stato</th>
            <th className="px-4 py-2 text-left">Creato il</th>
            <th className="px-4 py-2 text-center">Azioni</th>
          </tr>
        </thead>
        <tbody>
          {payments.map((payment) => (
            <tr key={payment.id} className="border-t hover:bg-gray-50">
              <td className="px-4 py-2 font-medium">{payment.amount} €</td>
              <td className="px-4 py-2 text-sm text-gray-600">{payment.method}</td>
              <td className="px-4 py-2">
                <span
                  className={`px-2 py-1 rounded text-xs ${
                    payment.status === "COMPLETED"
                      ? "bg-green-100 text-green-700"
                      : payment.status === "FAILED"
                      ? "bg-red-100 text-red-700"
                      : "bg-yellow-100 text-yellow-700"
                  }`}
                >
                  {payment.status}
                </span>
              </td>
              <td className="px-4 py-2 text-sm text-gray-600">
                {new Date(payment.createdAt).toLocaleDateString()}
              </td>
              <td className="px-4 py-2">
                <div className="flex gap-2 justify-center">
                  <button className="px-3 py-1 text-sm rounded bg-blue-500 text-white hover:bg-blue-600">
                    Dettagli
                  </button>
                  <button className="px-3 py-1 text-sm rounded bg-yellow-500 text-white hover:bg-yellow-600">
                    Modifica
                  </button>
                  <button className="px-3 py-1 text-sm rounded bg-red-500 text-white hover:bg-red-600">
                    Elimina
                  </button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}