package com.condominio.ConvivoApp.ticket.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String title;

    @Column(length = 2000)
    private String description;

    // Sarebbe meglio usare enum, ma per ora lasciamo String
    private String status;     // OPEN, IN_PROGRESS, CLOSED
    private String priority;   // LOW, MEDIUM, HIGH

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID createdBy;      // Utente che ha creato il ticket

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID condominiumId;  // Condominio di riferimento

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID unitId;         // Unità abitativa

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
