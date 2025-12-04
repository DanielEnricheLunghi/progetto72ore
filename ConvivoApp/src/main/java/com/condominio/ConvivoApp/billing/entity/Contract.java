package com.condominio.ConvivoApp.billing.entity;

import com.condominio.ConvivoApp.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un contratto firmato da un utente (amministratore o responsabile).
 * Un contratto può avere molte fatture associate.
 */
@Entity
@Table(
        name = "contracts",
        indexes = {
                @Index(name = "idx_contract_signed_by", columnList = "signed_by")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //* ID univoco del contratto (auto-increment in MySQL)

    /**
     * Utente che ha firmato il contratto.
     * Relazione molti-contratti verso uno User.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signed_by", nullable = false)
    private User signedBy;

    /**
     * Titolo del contratto.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String title;

    /**
     * Descrizione opzionale del contratto (testo lungo).
     */
    @Size(max = 10000)
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Data di inizio del contratto.
     */
    @Column(name = "start_date", columnDefinition = "DATETIME")
    private OffsetDateTime startDate;

    /**
     * Data di fine del contratto.
     */
    @Column(name = "end_date", columnDefinition = "DATETIME")
    private OffsetDateTime endDate;

    /**
     * Timestamp di creazione del record.
     */
    @Column(name = "created_at", columnDefinition = "DATETIME")
    private OffsetDateTime createdAt;

    /**
     * Lista delle fatture associate a questo contratto.
     * JsonIgnore evita ricorsione infinita nella serializzazione JSON.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invoice> invoices = new ArrayList<>();

    /**
     * Metodo eseguito prima del persist per settare createdAt.
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = OffsetDateTime.now();
    }
}
