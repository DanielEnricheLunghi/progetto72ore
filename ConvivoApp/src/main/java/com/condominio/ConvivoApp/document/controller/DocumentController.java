package com.condominio.ConvivoApp.document.controller;

import com.condominio.ConvivoApp.document.dto.DocumentDto;
import com.condominio.ConvivoApp.document.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * CRUD operations for Document (metadata)
 */
@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDto> create(@Valid @RequestBody DocumentDto dto, Authentication auth) {
        String userId = auth != null ? auth.getName() : "system";
        DocumentDto res = documentService.createDocument(dto, userId);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocument(id));
    }

    @GetMapping
    public ResponseEntity<Page<DocumentDto>> list(@RequestParam Long condoId, Pageable pageable) {
        return ResponseEntity.ok(documentService.listDocuments(condoId, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth) {
        String userId = auth != null ? auth.getName() : "system";
        documentService.deleteDocument(id, userId);
        return ResponseEntity.ok().build();
    }
}
