package com.condominio.ConvivoApp.document.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_audit", indexes = {
        @Index(name = "idx_audit_version", columnList = "version_id"),
        @Index(name = "idx_audit_document", columnList = "document_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DocumentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "version_id")
    private Long versionId;

    @Column(name = "action")
    private String action; // UPLOAD, DELETE, DOWNLOAD, PERMISSION_CHANGE

    @Column(name = "performed_by")
    private String performedBy;

    @Column(name = "details", columnDefinition = "TEXT")
    private String details;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
