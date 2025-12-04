package com.condominio.ConvivoApp.notification.service;

import com.condominio.ConvivoApp.notification.entity.NotificationAudit;

import java.util.UUID;

public interface NotificationAuditService {
    NotificationAudit recordAudit(UUID notificationId, UUID userId, String action);
}
