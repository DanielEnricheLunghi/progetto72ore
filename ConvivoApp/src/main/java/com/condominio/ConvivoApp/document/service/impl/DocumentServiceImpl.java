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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @Override
    @Transactional
    public DocumentDto uploadDocument(MultipartFile file, Long condoId, String documentType, String userId) {
        // 1. Caricare il file su S3 o su filesystem locale
        // Per test locale puoi salvarlo in una cartella:
        try {
            Path uploadDir = Paths.get("uploads");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            Path filePath = uploadDir.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // 2. Creare entità Document con metadati
            Document entity = new Document();
            entity.setFileName(file.getOriginalFilename());
            entity.setDocumentType(documentType);
            entity.setCondominiumId(condoId);
            entity.setOwnerId(userId);
            entity.setCreatedAt(LocalDateTime.now());
            entity.setUpdatedAt(LocalDateTime.now());
            entity.setFilePath(filePath.toString()); // se hai un campo per il path

            // 3. Salvare nel repository
            Document saved = documentRepository.save(entity);

            // 4. Audit
            auditService.record(saved.getId(), null, "DOCUMENT_UPLOADED", userId, "File uploaded");

            // 5. Restituire DTO
            return mapper.toDto(saved);

        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'upload del file", e);
        }
    }
}
