package com.condominio.ConvivoApp.document.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditDto {
    private Long id;
    private Long documentId;
    private Long versionId;
    private String action;
    private String performedBy;
    private String details;
    private LocalDateTime createdAt;
}
