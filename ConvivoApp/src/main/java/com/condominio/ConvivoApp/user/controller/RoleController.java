package com.condominio.ConvivoApp.user.controller;

import com.condominio.ConvivoApp.user.dto.RoleDTO;
import com.condominio.ConvivoApp.user.entity.Role;
import com.condominio.ConvivoApp.user.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Integer id) {
        return convertToDTO(roleService.getRoleById(id));
    }

    private RoleDTO convertToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName(), role.getDescription());
    }
}

