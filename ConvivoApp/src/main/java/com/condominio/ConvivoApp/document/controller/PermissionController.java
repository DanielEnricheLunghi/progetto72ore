package com.condominio.ConvivoApp.document.controller;

import com.condominio.ConvivoApp.document.entity.DocumentPermission;
import com.condominio.ConvivoApp.document.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents/{documentId}/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<DocumentPermission> grant(@PathVariable Long documentId,
                                                    @RequestParam String userId,
                                                    @RequestParam String role,
                                                    Authentication auth) {
        String grantedBy = auth != null ? auth.getName() : "system";
        DocumentPermission p = permissionService.grantPermission(documentId, userId, role, grantedBy);
        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<List<DocumentPermission>> list(@PathVariable Long documentId) {
        return ResponseEntity.ok(permissionService.listPermissions(documentId));
    }
}
