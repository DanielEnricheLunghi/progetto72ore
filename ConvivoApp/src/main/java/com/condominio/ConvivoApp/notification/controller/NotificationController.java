package com.condominio.ConvivoApp.notification.controller;

import com.condominio.ConvivoApp.notification.dto.CreateNotificationRequest;
import com.condominio.ConvivoApp.notification.dto.NotificationDto;
import com.condominio.ConvivoApp.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;



    @PostMapping
    @Operation(summary = "Crea una nuova notifica")
    @ApiResponse(responseCode = "200", description = "Notifica creata con successo")


    public ResponseEntity<NotificationDto> create(@RequestBody CreateNotificationRequest request) {
        NotificationDto dto = notificationService.createNotification(request);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Recupera notifiche per un utente")
    @ApiResponse(responseCode = "200", description = "Lista di notifiche restituita con successo")
    public ResponseEntity<List<NotificationDto>> getForUser(@Parameter(description = "UUID dell'utente", example = "42bb8607-d05b-11ee-bf3a-0242ac120002")
     @PathVariable UUID userId) {
        return ResponseEntity.ok(notificationService.getNotificationsForUser(userId));
    }

    @PostMapping("/{id}/read")
    @Operation(summary = "Segna una notifica come letta")
    @ApiResponse(responseCode = "204", description = "Notifica segnata come letta")
    public ResponseEntity<Void> markRead(@Parameter(description = "UUID della notifica", example = "b289f759-d03d-11ee-bf3a-0242ac120002")
     @PathVariable UUID id) {
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }
}
