package com.condominio.ConvivoApp.chat.service.impl;

import com.condominio.ConvivoApp.chat.dto.MessageDto;
import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import com.condominio.ConvivoApp.chat.entity.ChatMessage;
import com.condominio.ConvivoApp.chat.exception.MessageNotFoundException;
import com.condominio.ConvivoApp.chat.mapper.MessageMapper;
import com.condominio.ConvivoApp.chat.repository.ConversationRepository;
import com.condominio.ConvivoApp.chat.repository.MessageRepository;
import com.condominio.ConvivoApp.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service("chatMessageServiceImpl")


@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    @Qualifier("chatMessageMapperImpl")
    private final MessageMapper messageMapper;

    @Override
    public List<MessageDto> getMessages(UUID conversationId) {
        ChatConversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new MessageNotFoundException("Conversation not found"));
        return messageRepository.findByConversationOrderByCreatedAtAsc(conversation)
                .stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto sendMessage(MessageDto messageDto) {
        ChatConversation conversation = conversationRepository.findById(messageDto.getConversationId())
                .orElseThrow(() -> new MessageNotFoundException("Conversation not found"));
        ChatMessage message = messageMapper.toEntity(messageDto);
        message.setConversation(conversation);
        ChatMessage saved = messageRepository.save(message);
        return messageMapper.toDto(saved);
    }
}
