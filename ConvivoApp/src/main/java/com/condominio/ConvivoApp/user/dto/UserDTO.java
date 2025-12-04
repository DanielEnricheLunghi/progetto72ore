package com.condominio.ConvivoApp.user.dto;

import com.condominio.ConvivoApp.user.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private String displayName;
    private Set<RoleName> roles;
}
