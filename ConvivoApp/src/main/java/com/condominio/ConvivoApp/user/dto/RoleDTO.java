package com.condominio.ConvivoApp.user.dto;

import com.condominio.ConvivoApp.user.entity.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Integer id;
    private RoleName name;
    private String description;
}

