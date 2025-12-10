package com.condominio.ConvivoApp.document.dto;

import lombok.*;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class DocumentDto {
    private Long id;
    private Long condominiumId;
    private String title;
    private String description;
    private String ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Campi per gestione file
    private String fileName;       // <-- corretto, allineato all'entità
    private String filePath;
    private String documentType;
    private String mimeType;
    private Long sizeBytes;
    private String uploadedBy;
    private String s3Path;

    private List<DocumentVersionDto> versions;


}
