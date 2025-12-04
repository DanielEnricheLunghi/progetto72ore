package com.condominio.ConvivoApp.billing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Rappresenta un singolo pagamento effettuato per una fattura.
 */
@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(name = "idx_payment_invoice", columnList = "invoice_id"),
                @Index(name = "idx_payment_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID univoco del pagamento

    /**
     * Fattura a cui si riferisce il pagamento.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    /**
     * Metodo di pagamento (ENUM).
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PaymentMethod method;

    /**
     * Importo pagato.
     */
    @NotNull
    @DecimalMin("0.00")
    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    /**
     * Riferimento opzionale del pagamento (ID transazione, nota, ecc.).
     */
    @Size(max = 255)
    @Column(length = 255)
    private String reference;

    /**
     * Stato del pagamento (ENUM).
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    /**
     * Timestamp di creazione del pagamento.
     */
    @Column(name = "created_at", columnDefinition = "DATETIME")
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
