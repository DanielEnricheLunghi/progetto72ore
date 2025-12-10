export type PaymentMethod = "BONIFICO" | "CARD" | "CASH";
export type PaymentStatus = "PENDING" | "COMPLETED" | "FAILED";

export interface Payment {
  id: number;               // Long → number
  invoiceId: number;        // Long → number
  method: PaymentMethod;    // Enum
  amount: number;           // BigDecimal → number
  reference?: string;       // opzionale
  status: PaymentStatus;    // Enum
  createdAt: string;        // OffsetDateTime → ISO string
}

export interface PaymentCreate {
  invoiceId: number;
  method: PaymentMethod;
  amount: number;
  reference?: string;
}

export interface PaymentUpdate {
  method?: PaymentMethod;
  amount?: number;
  reference?: string;
  status?: PaymentStatus;
}