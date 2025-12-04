package com.condominio.ConvivoApp.document.service.impl;

import com.condominio.ConvivoApp.document.dto.AuditDto;
import com.condominio.ConvivoApp.document.entity.DocumentAudit;
import com.condominio.ConvivoApp.document.repository.DocumentAuditRepository;
import com.condominio.ConvivoApp.document.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final DocumentAuditRepository auditRepository;

    @Override
    public void record(Long documentId, Long versionId, String action, String performedBy, String details) {
        DocumentAudit a = DocumentAudit.builder()
                .documentId(documentId)
                .versionId(versionId)
                .action(action)
                .performedBy(performedBy)
                .details(details)
                .createdAt(LocalDateTime.now())
                .build();
        auditRepository.save(a);
    }

    @Override
    public List<AuditDto> getAudit(Long documentId) {
        return auditRepository.findByDocumentIdOrderByCreatedAtDesc(documentId)
                .stream()
                .map(a -> AuditDto.builder()
                        .id(a.getId())
                        .documentId(a.getDocumentId())
                        .versionId(a.getVersionId())
                        .action(a.getAction())
                        .performedBy(a.getPerformedBy())
                        .details(a.getDetails())
                        .createdAt(a.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
