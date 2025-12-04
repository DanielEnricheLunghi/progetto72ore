package com.condominio.ConvivoApp.billing.entity;

/**
 * Enum degli stati possibili di un pagamento.
 */
public enum PaymentStatus {
    PENDING,   // Pagamento in sospeso
    COMPLETED, // Pagamento completato
    FAILED     // Pagamento fallito
}
