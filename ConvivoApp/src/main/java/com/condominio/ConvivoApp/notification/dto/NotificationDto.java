package com.condominio.ConvivoApp.notification.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
    private UUID id;
    private UUID recipientId;
    private String title;
    private String body;
    private String type;
    private UUID referenceId;
    private boolean read;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
