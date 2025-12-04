package com.condominio.ConvivoApp.document.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentVersionDto {
    private Long id;              //* Identificativo univoco della versione
    private Long documentId;      //* ID del documento a cui appartiene
    private String versionNumber; //* Numero o etichetta della versione (es. "v1.0")
    private String createdBy;     //* Utente che ha creato la versione
    private LocalDateTime createdAt; //* Data di creazione della versione
}