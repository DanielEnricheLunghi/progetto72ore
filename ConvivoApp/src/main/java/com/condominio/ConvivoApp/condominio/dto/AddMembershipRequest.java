/*******************************************************
 ** AddMembershipRequest.java
 ** -----------------------------------------------------
 ** DTO per la creazione di una nuova membership.
 ** Contiene i dati inviati dal client per creare
 ** l'associazione utente ↔ unità.
 *******************************************************/


package com.condominio.ConvivoApp.condominio.dto; //* Package dto del modulo condominio

import jakarta.validation.constraints.*; //* Import delle annotazioni di validazione
import lombok.Data;                      //* Lombok: genera getter/setter/equals/hashCode/toString

import java.util.Map;

@Data //* Lombok: genera automaticamente tutti i metodi standard
public class AddMembershipRequest {

    @NotBlank //* UUID utente obbligatorio
    @Size(min = 36, max = 36) //* UUID standard con trattini → 36 caratteri
    private String userId; //* Identificativo utente (UUID)

    @NotNull //* ID unità obbligatorio
    private Long unitId; //* Identificativo dell'unità

    @NotBlank //* Ruolo obbligatorio
    @Size(max = 50) //* Max 50 caratteri
    private String roleInUnit; //* Ruolo dell'utente (es. "owner", "tenant")

    private boolean verified; //* Stato di verifica (true/false)


    private Map<String, Object> metadata; //* Metadati aggiuntivi



}