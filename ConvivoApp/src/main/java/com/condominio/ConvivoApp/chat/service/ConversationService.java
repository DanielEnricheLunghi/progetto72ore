package com.condominio.ConvivoApp.chat.service;

import com.condominio.ConvivoApp.chat.dto.ConversationDto;
import java.util.List;
import java.util.UUID;

public interface ConversationService {
    List<ConversationDto> getAllConversations(UUID userId);
    ConversationDto getConversation(UUID conversationId);
    ConversationDto createConversation(ConversationDto conversationDto);
}
