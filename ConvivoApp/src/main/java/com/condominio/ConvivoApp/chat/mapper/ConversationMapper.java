package com.condominio.ConvivoApp.chat.mapper;

import com.condominio.ConvivoApp.chat.dto.ConversationDto;
import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        implementationName = "ChatConversationMapperImpl", // 👈 nome diverso
        implementationPackage = "com.condominio.ConvivoApp.chat.mapper"
)


public interface ConversationMapper {


    @Mapping(target = "participants", source = "participants")
    ConversationDto toDto(ChatConversation conversation);

    @Mapping(target = "participants", source = "participants")
    ChatConversation toEntity(ConversationDto conversationDto);
}
