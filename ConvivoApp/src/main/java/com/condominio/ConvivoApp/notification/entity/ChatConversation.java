package com.condominio.ConvivoApp.notification.entity;

import com.condominio.ConvivoApp.notification.entity.ChatMessage;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "notification_conversations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatConversation {

    @Id
    @GeneratedValue
    private UUID id;   // Usa UUID senza strategy=IDENTITY

    @Column(name = "user_a", nullable = false)
    private UUID userA;

    @Column(name = "user_b", nullable = false)
    private UUID userB;

    @ElementCollection
    @CollectionTable(
            name = "conversation_participants",
            joinColumns = @JoinColumn(name = "conversation_id")
    )
    @Column(name = "participant_id", nullable = false)
    @Builder.Default
    private List<UUID> participants = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ChatMessage> messages = new ArrayList<>();
}