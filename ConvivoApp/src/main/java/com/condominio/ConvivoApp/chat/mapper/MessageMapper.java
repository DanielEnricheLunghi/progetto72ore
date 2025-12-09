package com.condominio.ConvivoApp.chat.mapper;

import com.condominio.ConvivoApp.chat.dto.MessageDto;
import com.condominio.ConvivoApp.chat.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(target = "conversation.id", source = "conversationId")
    MessageDto toDto(ChatMessage message);

    @Mapping(target = "conversation.id", source = "conversationId")
    ChatMessage toEntity(MessageDto dto);
}
