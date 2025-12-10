package com.condominio.ConvivoApp.document.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDto {
    private String filename;
    private String s3Path;
    private String mimeType;
    private Long sizeBytes;
    private String uploadedBy;
    private Long id;
    private Long condominiumId;
    private String title;
    private String description;
    private String ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<DocumentVersionDto> versions;
}
