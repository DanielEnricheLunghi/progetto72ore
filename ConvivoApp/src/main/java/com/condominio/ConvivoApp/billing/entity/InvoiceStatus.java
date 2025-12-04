package com.condominio.ConvivoApp.billing.entity;

/**
 * Enum che rappresenta lo stato di una fattura.
 */
public enum InvoiceStatus {
    PENDING, // Fattura in attesa di pagamento
    PAID,    // Fattura pagata
    OVERDUE  // Fattura scaduta/non pagata entro la data di scadenza
}
