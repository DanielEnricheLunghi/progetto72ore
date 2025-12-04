package com.condominio.ConvivoApp.condominio.entity;

import com.condominio.ConvivoApp.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_unit_membership")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUnitMembership {

    @EmbeddedId
    private UserUnitMembershipId id;

    @ManyToOne
    @MapsId("unitId")
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "role_in_unit")
    private String roleInUnit;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata;
}
