package com.condominio.ConvivoApp.notification.service;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import java.util.List;

public interface ConversationService {
    ConversationDto createConversation(Long userA, Long userB);
    List<ConversationDto> getConversationsForUser(Long userId);
    ConversationDto getConversation(Long conversationId);
}
