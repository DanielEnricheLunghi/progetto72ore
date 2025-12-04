package com.condominio.ConvivoApp.notification.service.impl;

import com.condominio.ConvivoApp.notification.entity.NotificationAudit;
import com.condominio.ConvivoApp.notification.repository.NotificationAuditRepository;
import com.condominio.ConvivoApp.notification.service.NotificationAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationAuditServiceImpl implements NotificationAuditService {

    private final NotificationAuditRepository auditRepository;

    @Override
    public NotificationAudit recordAudit(UUID notificationId, UUID userId, String action) {
        NotificationAudit audit = NotificationAudit.builder()
                .notificationId(notificationId)
                .userId(userId)
                .action(action)
                .createdAt(LocalDateTime.now())
                .build();
        return auditRepository.save(audit);
    }
}