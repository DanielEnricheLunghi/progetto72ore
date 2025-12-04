package com.condominio.ConvivoApp.notification.controller;

import com.condominio.ConvivoApp.notification.dto.ConversationDto;
import com.condominio.ConvivoApp.notification.dto.MessageDto;
import com.condominio.ConvivoApp.notification.service.ConversationService;
import com.condominio.ConvivoApp.notification.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ConversationService conversationService;
    private final MessageService messageService;


    @PostMapping("/conversation")
    public ResponseEntity<ConversationDto> createConversation(@RequestParam Long userA, @RequestParam Long userB) {
        return ResponseEntity.ok(conversationService.createConversation(userA, userB));
    }

    @GetMapping("/conversations/{userId}")
    public ResponseEntity<List<ConversationDto>> getConversations(@PathVariable Long userId) {
        return ResponseEntity.ok(conversationService.getConversationsForUser(userId));
    }

    @GetMapping("/messages/{conversationId}")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable Long conversationId) {
        return ResponseEntity.ok(messageService.getMessages(conversationId));
    }

    @PostMapping("/messages")
    public ResponseEntity<MessageDto> sendMessage(@RequestParam Long conversationId,
                                                  @RequestParam Long senderId,
                                                  @RequestParam String content) {
        return ResponseEntity.ok(messageService.sendMessage(conversationId, senderId, content));
    }
}
