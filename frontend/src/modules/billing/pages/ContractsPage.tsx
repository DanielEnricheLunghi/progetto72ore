import { useEffect, useState } from "react";
import ContractTable from "../components/ContractTable";
import { Contract } from "../types/contractTypes";
import { contractService } from "../services/contractService";

export default function ContractsPage() {
  const [contracts, setContracts] = useState<Contract[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    contractService.getAll()
      .then(setContracts)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div>Caricamento...</div>;
  if (error) return <div className="text-red-500">{error}</div>;

  return (
    <div className="p-6">
      <div className="mb-6 flex items-center justify-between">
        <h1 className="text-2xl font-semibold">Contratti</h1>
        <button className="px-4 py-2 rounded bg-green-600 text-white hover:bg-green-700">
          + Nuovo contratto
        </button>
      </div>
      <ContractTable contracts={contracts} />
    </div>
  );
}