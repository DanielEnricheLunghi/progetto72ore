package com.condominio.ConvivoApp.notification.service;

import com.condominio.ConvivoApp.notification.dto.CreateNotificationRequest;
import com.condominio.ConvivoApp.notification.dto.NotificationDto;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    // Crea una notifica a partire da un DTO di richiesta
    NotificationDto createNotification(CreateNotificationRequest request);

    // Recupera tutte le notifiche per un utente
    List<NotificationDto> getNotificationsForUser(UUID userId);

    // Segna una notifica come letta
    void markAsRead(UUID notificationId);

    // Invia una notifica diretta (parametri semplici)
    void sendNotification(UUID userId, String subject, String content);
}