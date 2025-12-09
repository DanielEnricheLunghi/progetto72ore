package com.condominio.ConvivoApp.notification.mapper;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import com.condominio.ConvivoApp.notification.entity.ChatConversation;
import org.mapstruct.Mapper;

import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        implementationName = "NotificationMapperImpl",
        implementationPackage = "com.condominio.ConvivoApp.notification.mapper"
)

public interface ConversationMapper {

    // Conversione entity -> DTO
    ConversationDto toDto(ChatConversation conv);

    // Conversione DTO -> entity
    ChatConversation toEntity(ConversationDto dto);
}