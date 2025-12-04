package com.condominio.ConvivoApp.billing.dto;

import com.condominio.ConvivoApp.billing.entity.PaymentMethod;
import com.condominio.ConvivoApp.billing.entity.PaymentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * DTO per la gestione dei pagamenti.
 * Utilizzato per comunicare dati tramite API senza esporre direttamente l'entity Payment.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class PaymentDTO {

    private Long id; // ID del pagamento

    @NotNull(message = "invoiceId è obbligatorio")
    private Long invoiceId; // ID della fattura a cui si riferisce

    @NotNull(message = "Il metodo di pagamento è obbligatorio")
    private PaymentMethod method; // Metodo di pagamento (BONIFICO, CARD, CASH)

    @NotNull(message = "L'importo non può essere nullo")
    @DecimalMin(value = "0.00", message = "L'importo deve essere maggiore o uguale a 0")
    @Positive(message = "amount must be greater than zero")
    private BigDecimal amount; // Importo del pagamento

    @Size(max = 255, message = "Il riferimento non può superare 255 caratteri")
    private String reference; // Riferimento opzionale

    @NotNull(message = "status è obbligatorio")
    private PaymentStatus status; // Stato del pagamento (PENDING, COMPLETED, FAILED)

    private OffsetDateTime createdAt; // Data creazione record
}