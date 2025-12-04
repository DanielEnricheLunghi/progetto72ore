package com.condominio.ConvivoApp.notification.service.impl;

import com.condominio.ConvivoApp.notification.dto.CreateNotificationRequest;
import com.condominio.ConvivoApp.notification.dto.NotificationDto;
import com.condominio.ConvivoApp.notification.entity.Notification;
import com.condominio.ConvivoApp.notification.mapper.NotificationMapper;
import com.condominio.ConvivoApp.notification.repository.NotificationRepository;
import com.condominio.ConvivoApp.notification.service.NotificationService;
import com.condominio.ConvivoApp.notification.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.condominio.ConvivoApp.notification.service.NotificationAuditService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final MailService mailService;
    private final NotificationAuditService notificationAuditService;

    @Override
    public NotificationDto createNotification(CreateNotificationRequest request) {
        Notification n = Notification.builder()
                .recipientId(request.getRecipientId())
                .title(request.getTitle())
                .body(request.getBody())
                .type(request.getType())
                .referenceId(request.getReferenceId())
                .read(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Notification saved = notificationRepository.save(n);

        //* delega al service audit
        notificationAuditService.recordAudit(saved.getId(), saved.getRecipientId(), "CREATED");

        if (request.isSendEmail()) {
            try {
                String to = resolveRecipientEmail(saved.getRecipientId());
                mailService.sendNotificationEmail(to, saved.getTitle(), saved.getBody());
            } catch (Exception e) {
                log.warn("Failed to send notification email for notificationId={}", saved.getId(), e);
            }
        }

        log.debug("Notification created id={} for user={}", saved.getId(), saved.getRecipientId());
        return notificationMapper.toDto(saved);
    }



    @Override
    @Transactional(readOnly = true)
    public List<NotificationDto> getNotificationsForUser(UUID userId) {
        return notificationRepository.findByRecipientIdOrderByCreatedAtDesc(userId)
                .stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(UUID notificationId) {
        Notification n = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("Notifica non trovata"));

        n.setRead(true);
        n.setUpdatedAt(LocalDateTime.now());
        notificationRepository.save(n);

        //* delega al service audit
        notificationAuditService.recordAudit(n.getId(), n.getRecipientId(), "MARKED_READ");
    }

    @Override
    public void sendNotification(UUID userId, String subject, String content) {
        Notification notification = Notification.builder()
                .recipientId(userId)
                .title(subject)
                .body(content)
                .type("INFO") // o un enum/valore più significativo
                .read(false)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Notification saved = notificationRepository.save(notification);

        //* delega al service audit
        notificationAuditService.recordAudit(saved.getId(), userId, "SENT");

        try {
            String to = resolveRecipientEmail(userId);
            mailService.sendNotificationEmail(to, subject, content);
        } catch (Exception e) {
            log.warn("Failed to send notification email for notificationId={}", saved.getId(), e);
        }

        log.debug("Notification sent id={} to user={}", saved.getId(), userId);
    }

    private String resolveRecipientEmail(UUID recipientId) {
        // TODO: integrazione con user-service per recuperare email reale
        return "user+" + recipientId.toString() + "@example.com";
    }
}