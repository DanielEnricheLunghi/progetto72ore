package com.condominio.ConvivoApp.notification.mapper;

import com.condominio.ConvivoApp.notification.dto.MessageDto;
import com.condominio.ConvivoApp.notification.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "conversationId", source = "conversation.id")
    MessageDto toDto(ChatMessage entity);

    @Mapping(target = "conversation", ignore = true)
    ChatMessage toEntity(MessageDto dto);
}
