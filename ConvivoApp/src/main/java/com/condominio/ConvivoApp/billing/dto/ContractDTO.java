package com.condominio.ConvivoApp.billing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO per la gestione dei contratti.
 * Utilizzato per inviare/ricevere dati via API senza esporre direttamente l'entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class ContractDTO {

    private Long id; // ID del contratto

    @NotNull(message = "signedById non può essere vuoto!")
    private UUID signedById;; // ID dell'utente che ha firmato il contratto

    @NotBlank(message = "Il titolo del contratto non può essere vuoto")
    @Size(min = 3, max = 100, message = "Il titolo deve essere minimo 3 caratteri e non può superare 100 caratteri")
    private String title; // Titolo del contratto

    @Size(max = 5000, message = "La descrizione non può superare 5000 caratteri")
    private String description; // Descrizione opzionale

    @NotNull(message = "startDate must not be null")
    private OffsetDateTime startDate; // Data di inizio contratto

    @NotNull(message = "endDate must not be null")
    private OffsetDateTime endDate; // Data di fine contratto

    private OffsetDateTime createdAt; // Data di creazione del record
}