package com.condominio.ConvivoApp.notification.repository;

import com.condominio.ConvivoApp.notification.entity.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository("notificationConversationRepository")
public interface ConversationRepository extends JpaRepository<ChatConversation, UUID> {
    List<ChatConversation> findByUserAOrUserB(UUID userA, UUID userB);

    List<ChatConversation> findByParticipantsContaining(UUID userId);
}

