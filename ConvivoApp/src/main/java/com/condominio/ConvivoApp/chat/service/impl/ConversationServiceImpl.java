package com.condominio.ConvivoApp.chat.service.impl;

import com.condominio.ConvivoApp.chat.dto.ConversationDto;
import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import com.condominio.ConvivoApp.chat.mapper.ConversationMapper;
import com.condominio.ConvivoApp.chat.repository.ConversationRepository;
import com.condominio.ConvivoApp.chat.service.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final ConversationMapper conversationMapper;

    @Override
    public List<ConversationDto> getAllConversations(UUID userId) {
        return conversationRepository.findAll()
                .stream()
                .filter(c -> c.getParticipants().contains(userId))
                .map(conversationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConversationDto getConversation(UUID conversationId) {
        ChatConversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ConversationNotFoundException("Conversation not found"));
        return conversationMapper.toDto(conversation);
    }

    @Override
    public ConversationDto createConversation(ConversationDto conversationDto) {
        ChatConversation entity = conversationMapper.toEntity(conversationDto);
        ChatConversation saved = conversationRepository.save(entity);
        return conversationMapper.toDto(saved);
    }
}
