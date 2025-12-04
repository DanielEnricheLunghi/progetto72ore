package com.condominio.ConvivoApp.notification.repository;

import com.condominio.ConvivoApp.notification.entity.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<ChatConversation, Long> {
    List<ChatConversation> findByUserAOrUserB(Long userA, Long userB);
}

