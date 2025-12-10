import {
  Contract,
  ContractCreate,
  ContractUpdate,
} from "../types/contractTypes";

const BASE_URL = "http://localhost:8080/api/contracts";

export const contractService = {
  // -------------------------
  // READ
  // -------------------------
  async getAll(): Promise<Contract[]> {
    const response = await fetch(BASE_URL);

    if (!response.ok) {
      throw new Error("Errore nel recupero dei contratti");
    }

    return response.json();
  },

  async getById(id: number): Promise<Contract> {
    const response = await fetch(`${BASE_URL}/${id}`);

    if (!response.ok) {
      throw new Error("Contratto non trovato");
    }

    return response.json();
  },

  // -------------------------
  // CREATE
  // -------------------------
  async create(payload: ContractCreate): Promise<Contract> {
    const response = await fetch(BASE_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      throw new Error("Errore nella creazione del contratto");
    }

    return response.json();
  },

  // -------------------------
  // UPDATE
  // -------------------------
  async update(
    id: number,
    payload: ContractUpdate
  ): Promise<Contract> {
    const response = await fetch(`${BASE_URL}/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    });

    if (!response.ok) {
      throw new Error("Errore nell'aggiornamento del contratto");
    }

    return response.json();
  },

  // -------------------------
  // DELETE
  // -------------------------
  async remove(id: number): Promise<void> {
    const response = await fetch(`${BASE_URL}/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error("Errore nell'eliminazione del contratto");
    }
  },
};
