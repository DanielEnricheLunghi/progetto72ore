package com.condominio.ConvivoApp.notification.service;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import java.util.List;
import java.util.UUID;

public interface ConversationService {
    ConversationDto createConversation(ConversationDto dto);
    List<ConversationDto> getConversationsForUser(UUID userId);
    ConversationDto getConversation(UUID conversationId);
    List<ConversationDto> getAllConversations(UUID userId);
}
