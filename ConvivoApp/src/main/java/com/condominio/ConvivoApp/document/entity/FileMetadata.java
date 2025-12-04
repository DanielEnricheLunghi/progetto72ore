package com.condominio.ConvivoApp.document.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file_metadata")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="version_id", nullable = false)
    private Long versionId;

    @Column(columnDefinition = "TEXT")
    private String jsonMetadata;
}
