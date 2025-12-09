package com.condominio.ConvivoApp.chat.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationDto {
    private UUID id;
    private String title;
    private List<UUID> participants;
    private LocalDateTime createdAt;
}



