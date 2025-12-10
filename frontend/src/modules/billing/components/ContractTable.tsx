import React from "react";
import { Contract } from "../types/contractTypes";

interface Props {
  contracts: Contract[];
}

export default function ContractTable({ contracts }: Props) {
  if (contracts.length === 0) {
    return (
      <div className="text-center text-gray-500 py-6">
        Nessun contratto presente
      </div>
    );
  }

  return (
    <div className="overflow-x-auto">
      <table className="min-w-full border border-gray-200 rounded-lg">
        <thead className="bg-gray-100">
          <tr>
            <th className="px-4 py-2 text-left">Titolo</th>
            <th className="px-4 py-2 text-left">Periodo</th>
            <th className="px-4 py-2 text-left">Creato il</th>
            <th className="px-4 py-2 text-center">Azioni</th>
          </tr>
        </thead>

        <tbody>
          {contracts.map((contract) => (
            <tr
              key={contract.id}
              className="border-t hover:bg-gray-50"
            >
              <td className="px-4 py-2 font-medium">
                {contract.title}
              </td>

              <td className="px-4 py-2 text-sm text-gray-600">
                {new Date(contract.startDate).toLocaleDateString()} →{" "}
                {new Date(contract.endDate).toLocaleDateString()}
              </td>

              <td className="px-4 py-2 text-sm text-gray-600">
                {new Date(contract.createdAt).toLocaleDateString()}
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