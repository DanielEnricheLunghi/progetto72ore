package com.condominio.ConvivoApp.notification.service.impl;

import com.condominio.ConvivoApp.notification.dto.MessageDto;
import com.condominio.ConvivoApp.notification.entity.ChatConversation;
import com.condominio.ConvivoApp.notification.entity.ChatMessage;
import com.condominio.ConvivoApp.notification.mapper.MessageMapper;
import com.condominio.ConvivoApp.notification.repository.ConversationRepository;
import com.condominio.ConvivoApp.notification.repository.MessageRepository;
import com.condominio.ConvivoApp.notification.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("notificationMessageServiceImpl")
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final MessageMapper messageMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public MessageDto sendMessage(UUID conversationId, UUID senderId, String content) {
        ChatConversation conv = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found"));

        ChatMessage message = ChatMessage.builder()
                .conversation(conv)
                .senderId(senderId)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        ChatMessage saved = messageRepository.save(message);
        MessageDto dto = messageMapper.toDto(saved);

        messagingTemplate.convertAndSend("/topic/conversation/" + conversationId, dto);
        log.debug("Message sent in conversation {} by {}: {}", conversationId, senderId, saved.getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MessageDto> getMessages(UUID conversationId) {
        return messageRepository.findByConversation_IdOrderByCreatedAtAsc(conversationId)
                .stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }
}