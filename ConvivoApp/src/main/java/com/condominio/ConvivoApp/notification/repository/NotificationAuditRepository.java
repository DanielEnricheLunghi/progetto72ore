package com.condominio.ConvivoApp.notification.repository;

import com.condominio.ConvivoApp.notification.entity.NotificationAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationAuditRepository extends JpaRepository<NotificationAudit, Long> {}
