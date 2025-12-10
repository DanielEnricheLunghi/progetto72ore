import { Invoice, InvoiceCreate, InvoiceUpdate } from "../types/invoiceTypes";

const BASE_URL = "http://localhost:8080/api/invoices";

export const invoiceService = {
  async getAll(): Promise<Invoice[]> {
    const response = await fetch(BASE_URL);
    if (!response.ok) throw new Error("Errore nel recupero delle fatture");
    return response.json();
  },

  async getById(id: number): Promise<Invoice> {
    const response = await fetch(`${BASE_URL}/${id}`);
    if (!response.ok) throw new Error("Fattura non trovata");
    return response.json();
  },

  async create(payload: InvoiceCreate): Promise<Invoice> {
    const response = await fetch(BASE_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    if (!response.ok) throw new Error("Errore nella creazione della fattura");
    return response.json();
  },

  async update(id: number, payload: InvoiceUpdate): Promise<Invoice> {
    const response = await fetch(`${BASE_URL}/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    if (!response.ok) throw new Error("Errore nell'aggiornamento della fattura");
    return response.json();
  },

  async remove(id: number): Promise<void> {
    const response = await fetch(`${BASE_URL}/${id}`, { method: "DELETE" });
    if (!response.ok) throw new Error("Errore nell'eliminazione della fattura");
  },
};