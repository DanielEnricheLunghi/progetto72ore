package com.condominio.ConvivoApp.document.service;

import com.condominio.ConvivoApp.document.dto.AuditDto;
import java.util.List;

public interface AuditService {
    void record(Long documentId, Long versionId, String action, String performedBy, String details);
    List<AuditDto> getAudit(Long documentId);
}
