package com.condominio.ConvivoApp.document.repository;

import com.condominio.ConvivoApp.document.entity.DocumentAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentAuditRepository extends JpaRepository<DocumentAudit, Long> {
    List<DocumentAudit> findByDocumentIdOrderByCreatedAtDesc(Long documentId);
}
