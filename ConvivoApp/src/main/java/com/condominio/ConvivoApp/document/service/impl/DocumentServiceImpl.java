package com.condominio.ConvivoApp.document.service.impl;

import com.condominio.ConvivoApp.document.dto.DocumentDto;
import com.condominio.ConvivoApp.document.entity.Document;
import com.condominio.ConvivoApp.document.mapper.DocumentMapper;
import com.condominio.ConvivoApp.document.repository.DocumentRepository;
import com.condominio.ConvivoApp.document.service.AuditService;
import com.condominio.ConvivoApp.document.service.DocumentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper mapper;
    private final AuditService auditService;

    @Override
    @Transactional
    public DocumentDto createDocument(DocumentDto dto, String creatorId) {
        Document entity = mapper.toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setOwnerId(creatorId);
        Document saved = documentRepository.save(entity);
        auditService.record(saved.getId(), null, "DOCUMENT_CREATED", creatorId, "Document created");
        return mapper.toDto(saved);
    }

    @Override
    public DocumentDto getDocument(Long documentId) {
        Document d = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found: " + documentId));
        return mapper.toDto(d);
    }

    @Override
    public Page<DocumentDto> listDocuments(Long condominiumId, Pageable pageable) {
        Page<Document> page = new PageImpl<>(documentRepository.findByCondominiumId(condominiumId), pageable, 0);
        return page.map(mapper::toDto);
    }

    @Override
    @Transactional
    public void deleteDocument(Long documentId, String actorId) {
        Document d = documentRepository.findById(documentId).orElseThrow(() -> new IllegalArgumentException("Not found"));
        d.setDeletedAt(LocalDateTime.now());
        documentRepository.save(d);
        auditService.record(documentId, null, "DOCUMENT_DELETED", actorId, "Deleted logical");
    }
}
