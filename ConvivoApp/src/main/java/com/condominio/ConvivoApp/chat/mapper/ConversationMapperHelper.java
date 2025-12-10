package com.condominio.ConvivoApp.chat.mapper;

import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import com.condominio.ConvivoApp.chat.exception.ConversationNotFoundException;
import com.condominio.ConvivoApp.chat.repository.ConversationRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConversationMapperHelper {

    private final ConversationRepository repo;

    public ConversationMapperHelper(ConversationRepository repo) {
        this.repo = repo;
    }

    public ChatConversation map(UUID id) {
        return repo.findById(id)
                .orElseThrow(() -> new ConversationNotFoundException("Conversation not found with id: " + id));
    }
}