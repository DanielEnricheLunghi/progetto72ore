package com.condominio.ConvivoApp.document.service;

import com.condominio.ConvivoApp.document.dto.DocumentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    DocumentDto createDocument(DocumentDto dto, String creatorId);
    DocumentDto getDocument(Long documentId);
    Page<DocumentDto> listDocuments(Long condominiumId, Pageable pageable);
    void deleteDocument(Long documentId, String actorId);
    // nuovo metodo per upload
    DocumentDto uploadDocument(MultipartFile file, Long condoId, String documentType, String userId);
}
