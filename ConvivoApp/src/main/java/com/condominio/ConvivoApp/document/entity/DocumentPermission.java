package com.condominio.ConvivoApp.document.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_permissions", indexes = {
        @Index(name = "idx_perm_document", columnList = "document_id"),
        @Index(name = "idx_perm_user", columnList = "user_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DocumentPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="document_id", nullable = false)
    private Long documentId;

    @Column(name="user_id", nullable = false)
    private String userId;

    @Column(name="role")
    private String role; // OWNER, VIEWER, EDITOR

    @Column(name="granted_by")
    private String grantedBy;

    @Column(name="granted_at")
    private LocalDateTime grantedAt;
}
