package com.condominio.ConvivoApp.notification.repository;

import com.condominio.ConvivoApp.notification.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository("notificationMessageRepository")
public interface MessageRepository extends JpaRepository<ChatMessage, UUID> {

    //*Recupera tutti i messaggi di una conversazione ordinati per data di creazione (ASC)
    List<ChatMessage> findByConversation_IdOrderByCreatedAtAsc(UUID conversationId);

}
