package com.condominio.ConvivoApp.chat.repository;

import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;

@Repository("chatConversationRepository")

public interface ConversationRepository extends JpaRepository<ChatConversation, UUID> {
    List<ChatConversation> findByParticipantsContaining(UUID userId);

}
