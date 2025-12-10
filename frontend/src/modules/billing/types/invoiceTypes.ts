export type InvoiceStatus = "PENDING" | "PAID" | "OVERDUE";

export interface Invoice {
  id: number;               // Long → number
  contractId: number;       // Long → number
  amount: number;           // BigDecimal → number (in €)
  dueDate: string | null;   // OffsetDateTime → ISO string
  paidDate?: string | null; // OffsetDateTime → ISO string, opzionale
  status: InvoiceStatus;    // Enum
  reference?: string;       // opzionale
  createdAt: string;        // OffsetDateTime → ISO string
}

export interface InvoiceCreate {
  contractId: number;
  amount: number;
  dueDate: string;
  reference?: string;
}

export interface InvoiceUpdate {
  amount?: number;
  dueDate?: string;
  paidDate?: string;
  status?: InvoiceStatus;
  reference?: string;
}