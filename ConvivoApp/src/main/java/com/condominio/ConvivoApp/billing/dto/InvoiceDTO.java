package com.condominio.ConvivoApp.billing.dto;

import com.condominio.ConvivoApp.billing.entity.InvoiceStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * DTO per la gestione delle fatture.
 * Invia/riceve dati senza esporre direttamente l'entity Invoice.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class InvoiceDTO {

    private Long id; // ID della fattura

    @NotNull(message = "contractId è obbligatorio!")
    private Long contractId; // ID del contratto associato

    @NotNull(message = "L'importo non può essere nullo")
    @DecimalMin(value = "0.00", message = "L'importo deve essere maggiore o uguale a 0")
    @Positive(message = "amount must be greater than zero")
    private BigDecimal amount; // Importo della fattura

    private OffsetDateTime dueDate; // Data di scadenza

    private OffsetDateTime paidDate; // Data pagamento (null se non ancora pagata)

    @NotNull(message = "status è obbligatorio")
    private InvoiceStatus status; // Stato della fattura (PENDING, PAID, OVERDUE)

    @Size(max = 255, message = "Il riferimento non può superare 255 caratteri")
    private String reference; // Riferimento opzionale della fattura

    private OffsetDateTime createdAt; // Data creazione record
}