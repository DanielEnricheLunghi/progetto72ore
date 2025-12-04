package com.condominio.ConvivoApp.notification.mapper;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import com.condominio.ConvivoApp.notification.entity.ChatConversation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",              // genera un bean Spring
        uses = {MessageMapper.class},           // usa altri mapper
        unmappedTargetPolicy = ReportingPolicy.IGNORE // ignora campi non mappati
)
public interface ConversationMapper {

    // Conversione entity -> DTO
    ConversationDto toDto(ChatConversation conv);

    // Conversione DTO -> entity
    ChatConversation toEntity(ConversationDto dto);
}