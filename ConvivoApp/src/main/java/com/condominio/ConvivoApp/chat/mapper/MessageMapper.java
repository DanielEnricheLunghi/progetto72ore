package com.condominio.ConvivoApp.chat.mapper;

import com.condominio.ConvivoApp.chat.dto.MessageDto;
import com.condominio.ConvivoApp.chat.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ConversationMapperHelper.class},
        implementationName = "ChatMessageMapperImpl",
        implementationPackage = "com.condominio.ConvivoApp.chat.mapper"
)
public interface MessageMapper {

    //* Entity → DTO
    @Mapping(source = "conversation.id", target = "conversationId")
    MessageDto toDto(ChatMessage message);

    //* DTO → Entity (usa ConversationMapperHelper per risolvere l'UUID in ChatConversation)
    @Mapping(source = "conversationId", target = "conversation")
    ChatMessage toEntity(MessageDto dto);
}