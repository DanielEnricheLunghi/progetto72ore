package com.condominio.ConvivoApp.chat.mapper;

import com.condominio.ConvivoApp.chat.dto.ConversationDto;
import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ConversationMapper {

    ConversationMapper INSTANCE = Mappers.getMapper(ConversationMapper.class);

    @Mapping(target = "participants", source = "participants")
    ConversationDto toDto(ChatConversation conversation);

    @Mapping(target = "participants", source = "participants")
    ChatConversation toEntity(ConversationDto conversationDto);
}
