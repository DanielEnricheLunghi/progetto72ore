package com.condominio.ConvivoApp.document.dto;

import lombok.*;
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
    private List<DocumentVersionDto> versions;
}
