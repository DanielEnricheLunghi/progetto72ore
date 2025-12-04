package com.condominio.ConvivoApp.notification.repository;

import com.condominio.ConvivoApp.notification.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
    // usa nested property according to JPA property traversal
    List<ChatMessage> findByConversation_IdOrderByTimestampAsc(Long conversationId);
}
