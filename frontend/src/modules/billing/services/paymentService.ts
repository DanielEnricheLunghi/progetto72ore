import { Payment, PaymentCreate, PaymentUpdate } from "../types/paymentTypes";

const BASE_URL = "http://localhost:8080/api/payments";

export const paymentService = {
  async getAll(): Promise<Payment[]> {
    const response = await fetch(BASE_URL);
    if (!response.ok) throw new Error("Errore nel recupero dei pagamenti");
    return response.json();
  },

  async getById(id: number): Promise<Payment> {
    const response = await fetch(`${BASE_URL}/${id}`);
    if (!response.ok) throw new Error("Pagamento non trovato");
    return response.json();
  },

  async create(payload: PaymentCreate): Promise<Payment> {
    const response = await fetch(BASE_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    if (!response.ok) throw new Error("Errore nella creazione del pagamento");
    return response.json();
  },

  async update(id: number, payload: PaymentUpdate): Promise<Payment> {
    const response = await fetch(`${BASE_URL}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    if (!response.ok) throw new Error("Errore nell'aggiornamento del pagamento");
    return response.json();
  },

  async remove(id: number): Promise<void> {
    const response = await fetch(`${BASE_URL}/${id}`, { method: "DELETE" });
    if (!response.ok) throw new Error("Errore nell'eliminazione del pagamento");
  },
};