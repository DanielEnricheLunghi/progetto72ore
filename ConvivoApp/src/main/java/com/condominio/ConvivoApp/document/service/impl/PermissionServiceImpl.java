package com.condominio.ConvivoApp.document.service.impl;

import com.condominio.ConvivoApp.document.entity.DocumentPermission;
import com.condominio.ConvivoApp.document.repository.DocumentPermissionRepository;
import com.condominio.ConvivoApp.document.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final DocumentPermissionRepository permissionRepository;

    @Override
    public DocumentPermission grantPermission(Long documentId, String userId, String role, String grantedBy) {
        DocumentPermission p = DocumentPermission.builder()
                .documentId(documentId)
                .userId(userId)
                .role(role)
                .grantedBy(grantedBy)
                .grantedAt(LocalDateTime.now())
                .build();
        return permissionRepository.save(p);
    }

    @Override
    public List<DocumentPermission> listPermissions(Long documentId) {
        return permissionRepository.findByDocumentId(documentId);
    }

    @Override
    public boolean hasAccess(Long documentId, String userId, String requiredRole) {
        return permissionRepository.findByDocumentIdAndUserId(documentId, userId)
                .map(p -> p.getRole().equalsIgnoreCase(requiredRole) || p.getRole().equalsIgnoreCase("OWNER"))
                .orElse(false);
    }
}
