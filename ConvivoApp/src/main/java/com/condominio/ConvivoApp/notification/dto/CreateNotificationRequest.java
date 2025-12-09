package com.condominio.ConvivoApp.notification.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateNotificationRequest {
    private UUID recipientId;
    private String title;
    private String body;
    private String type;
    private UUID referenceId;
    private boolean sendEmail;
}
