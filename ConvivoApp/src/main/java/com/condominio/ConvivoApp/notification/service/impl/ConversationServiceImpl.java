package com.condominio.ConvivoApp.notification.service.impl;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import com.condominio.ConvivoApp.notification.entity.ChatConversation;
import com.condominio.ConvivoApp.notification.mapper.ConversationMapper;
import com.condominio.ConvivoApp.notification.repository.ConversationRepository;
import com.condominio.ConvivoApp.notification.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationMapper conversationMapper;


    @Override
    public ConversationDto createConversation(Long userA, Long userB) {
        ChatConversation conv = ChatConversation.builder()
                .userA(userA)
                .userB(userB)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        ChatConversation saved = conversationRepository.save(conv);
        log.debug("Conversation created id={} between {} and {}", saved.getId(), userA, userB);
        return conversationMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConversationDto> getConversationsForUser(Long userId) {
        return conversationRepository.findByUserAOrUserB(userId, userId)
                .stream()
                .map(conversationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ConversationDto getConversation(Long conversationId) {
        ChatConversation conv = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new IllegalArgumentException("Conversation not found"));
        return conversationMapper.toDto(conv);
    }
}
