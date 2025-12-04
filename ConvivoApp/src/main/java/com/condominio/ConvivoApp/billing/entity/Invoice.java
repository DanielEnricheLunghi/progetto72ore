package com.condominio.ConvivoApp.billing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta una fattura generata sotto un contratto.
 */
@Entity
@Table(
        name = "invoices",
        indexes = {
                @Index(name = "idx_invoice_contract", columnList = "contract_id"),
                @Index(name = "idx_invoice_status", columnList = "status")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID univoco della fattura

    /**
     * Contratto a cui appartiene la fattura.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;

    /**
     * Importo totale della fattura.
     */
    @NotNull
    @DecimalMin("0.00")
    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    /**
     * Data di scadenza della fattura.
     */
    @Column(name = "due_date", columnDefinition = "DATETIME")
    private OffsetDateTime dueDate;

    /**
     * Data in cui la fattura è stata pagata (null se non ancora pagata).
     */
    @Column(name = "paid_date", columnDefinition = "DATETIME")
    private OffsetDateTime paidDate;

    /**
     * Stato della fattura (ENUM).
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private InvoiceStatus status = InvoiceStatus.PENDING;

    /**
     * Riferimento opzionale della fattura (es. numero identificativo).
     */
    @Size(max = 255)
    @Column(length = 255)
    private String reference;

    /**
     * Timestamp di creazione della fattura.
     */
    @Column(name = "created_at", columnDefinition = "DATETIME")
    private OffsetDateTime createdAt;

    /**
     * Pagamenti collegati a questa fattura.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
