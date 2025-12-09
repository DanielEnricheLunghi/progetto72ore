package com.condominio.ConvivoApp.chat.repository;

import com.condominio.ConvivoApp.chat.entity.ChatConversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<ChatConversation, UUID> { }
