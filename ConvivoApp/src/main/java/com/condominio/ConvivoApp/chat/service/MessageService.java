package com.condominio.ConvivoApp.chat.service;

import com.condominio.ConvivoApp.chat.dto.MessageDto;
import java.util.List;
import java.util.UUID;

public interface MessageService {
    List<MessageDto> getMessages(UUID conversationId);
    MessageDto sendMessage(MessageDto messageDto);
}

