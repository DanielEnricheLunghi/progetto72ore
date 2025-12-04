package com.condominio.ConvivoApp.document.service;

import com.condominio.ConvivoApp.document.entity.DocumentPermission;
import java.util.List;

public interface PermissionService {
    DocumentPermission grantPermission(Long documentId, String userId, String role, String grantedBy);
    List<DocumentPermission> listPermissions(Long documentId);
    boolean hasAccess(Long documentId, String userId, String requiredRole);
}
