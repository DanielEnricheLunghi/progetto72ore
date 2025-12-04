package com.condominio.ConvivoApp.user.service;

import com.condominio.ConvivoApp.user.entity.Role;
import com.condominio.ConvivoApp.user.entity.RoleName;
import com.condominio.ConvivoApp.user.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
    }

    public Role getRoleByName(RoleName name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Ruolo non trovato"));
    }
}

