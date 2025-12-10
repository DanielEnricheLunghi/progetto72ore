import ContractTable from "../components/ContractTable";
import { Contract } from "../types/contractTypes";

//Dati fittizi al fine di test
export default function ContractsPage() {
  const contracts: Contract[] = [
    {
      id: 1,
      title: "Contratto Energia 2024",
      startDate: "2024-01-01",
      endDate: "2024-12-31",
      createdAt: "2023-12-15",
    },
    {
      id: 2,
      title: "Manutenzione Software",
      startDate: "2024-03-01",
      endDate: "2025-03-01",
      createdAt: "2024-02-10",
    },
  ];

  return (
    <div className="p-6">
      <div className="mb-6 flex items-center justify-between">
        <h1 className="text-2xl font-semibold">
          Contratti
        </h1>

        <button className="px-4 py-2 rounded bg-green-600 text-white hover:bg-green-700">
          + Nuovo contratto
        </button>
      </div>

      <ContractTable contracts={contracts} />
    </div>
  );
}
