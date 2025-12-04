package com.condominio.ConvivoApp.notification.service;

public interface MailService {
    void sendNotificationEmail(String to, String subject, String body);
}
