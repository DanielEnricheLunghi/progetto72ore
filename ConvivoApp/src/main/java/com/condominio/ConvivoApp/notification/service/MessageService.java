package com.condominio.ConvivoApp.notification.service;

import com.condominio.ConvivoApp.notification.dto.MessageDto;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageDto sendMessage(UUID conversationId, UUID senderId, String content);
    List<MessageDto> getMessages(UUID conversationId);
}