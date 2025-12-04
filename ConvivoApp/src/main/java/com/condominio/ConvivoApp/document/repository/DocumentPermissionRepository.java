package com.condominio.ConvivoApp.document.repository;

import com.condominio.ConvivoApp.document.entity.DocumentPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DocumentPermissionRepository extends JpaRepository<DocumentPermission, Long> {
    List<DocumentPermission> findByDocumentId(Long documentId);
    Optional<DocumentPermission> findByDocumentIdAndUserId(Long documentId, String userId);
}
