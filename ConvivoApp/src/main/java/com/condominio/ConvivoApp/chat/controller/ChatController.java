package com.condominio.ConvivoApp.chat.controller;

import com.condominio.ConvivoApp.chat.dto.ConversationDto;
import com.condominio.ConvivoApp.chat.dto.MessageDto;
import com.condominio.ConvivoApp.chat.service.ConversationService;
import com.condominio.ConvivoApp.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ConversationService conversationService;
    private final MessageService messageService;

    // -------------------- CONVERSATIONS --------------------

    @GetMapping("/conversations/{userId}")
    public List<ConversationDto> getConversations(@PathVariable UUID userId) {
        return conversationService.getAllConversations(userId);
    }

    @GetMapping("/conversations/detail/{conversationId}")
    public ConversationDto getConversation(@PathVariable UUID conversationId) {
        return conversationService.getConversation(conversationId);
    }

    @PostMapping("/conversations")
    public ConversationDto createConversation(@RequestBody ConversationDto conversationDto) {
        return conversationService.createConversation(conversationDto);
    }

    // -------------------- MESSAGES --------------------

    @GetMapping("/messages/{conversationId}")
    public List<MessageDto> getMessages(@PathVariable UUID conversationId) {
        return messageService.getMessages(conversationId);
    }

    @PostMapping("/messages")
    public MessageDto sendMessage(@RequestBody MessageDto messageDto) {
        return messageService.sendMessage(messageDto);
    }

    // -------------------- REALTIME WEBSOCKET --------------------

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public MessageDto sendRealtimeMessage(MessageDto messageDto) {
        // Invia anche al database tramite service
        return messageService.sendMessage(messageDto);
    }
}
