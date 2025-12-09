package com.condominio.ConvivoApp.notification.controller;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import com.condominio.ConvivoApp.notification.dto.MessageDto;
import com.condominio.ConvivoApp.notification.service.ConversationService;
import com.condominio.ConvivoApp.notification.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notification/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ConversationService conversationService;
    private final MessageService messageService;

    @PostMapping("/conversation")
    public ResponseEntity<ConversationDto> createConversation(@RequestParam UUID userA,
                                                              @RequestParam UUID userB) {
        ConversationDto dto = ConversationDto.builder()
                .userA(userA)
                .userB(userB)
                .build();

        return ResponseEntity.ok(conversationService.createConversation(dto));
    }

    @GetMapping("/conversations/{userId}")
    public ResponseEntity<List<ConversationDto>> getConversations(@PathVariable UUID userId) {
        return ResponseEntity.ok(conversationService.getConversationsForUser(userId));
    }

    @GetMapping("/messages/{conversationId}")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable UUID conversationId) {
        return ResponseEntity.ok(messageService.getMessages(conversationId));
    }

    @PostMapping("/messages")
    public ResponseEntity<MessageDto> sendMessage(@RequestParam UUID conversationId,
                                                  @RequestParam UUID senderId,
                                                  @RequestParam String content) {
        return ResponseEntity.ok(messageService.sendMessage(conversationId, senderId, content));
    }
}
