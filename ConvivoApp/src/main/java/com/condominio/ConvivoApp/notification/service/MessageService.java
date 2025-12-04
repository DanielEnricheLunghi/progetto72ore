package com.condominio.ConvivoApp.notification.service;

import com.condominio.ConvivoApp.notification.dto.MessageDto;
import java.util.List;

public interface MessageService {
    MessageDto sendMessage(Long conversationId, Long senderId, String content);
    List<MessageDto> getMessages(Long conversationId);
}
