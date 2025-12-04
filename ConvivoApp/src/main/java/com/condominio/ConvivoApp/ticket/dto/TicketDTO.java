package com.condominio.ConvivoApp.ticket.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
    private UUID id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private UUID createdBy;
    private UUID condominiumId;
    private UUID unitId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
