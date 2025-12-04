package com.condominio.ConvivoApp.notification.mapper;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import com.condominio.ConvivoApp.notification.dto.MessageDto;
import com.condominio.ConvivoApp.notification.entity.ChatConversation;
import com.condominio.ConvivoApp.notification.entity.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ConversationDto toDto(ChatConversation conversation);

    MessageDto toDto(ChatMessage message);
}
