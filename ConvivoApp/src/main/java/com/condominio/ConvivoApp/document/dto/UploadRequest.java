package com.condominio.ConvivoApp.document.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO per upload: controller riceverà MultipartFile e metadata
 * Nota: non serializzabile via JSON, usato solo come binder lato controller
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadRequest {
    private MultipartFile file;
    private String description;
    private String createdBy;
}
