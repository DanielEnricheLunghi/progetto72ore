package com.condominio.ConvivoApp.chat.repository;

import com.condominio.ConvivoApp.chat.entity.ChatMessage;
import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("chatMessageRepository")
public interface MessageRepository extends JpaRepository<ChatMessage, UUID> {
    List<ChatMessage> findByConversationOrderByCreatedAtAsc(ChatConversation conversation);
}
