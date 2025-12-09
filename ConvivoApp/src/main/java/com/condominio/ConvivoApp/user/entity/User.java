package com.condominio.ConvivoApp.user.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;          // ⭐ AGGIUNTA

    @Column(name = "display_name", length = 255)
    private String displayName;

    @Column(length = 30)
    private String phone;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "profile_json", columnDefinition = "JSON")
    private String profileJson;

    @Column(name = "vat", length = 50)
    private String vat;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
    }
}
