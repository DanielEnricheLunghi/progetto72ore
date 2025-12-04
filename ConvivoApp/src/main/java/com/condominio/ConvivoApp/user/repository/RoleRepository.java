package com.condominio.ConvivoApp.user.repository;

import com.condominio.ConvivoApp.user.entity.Role;
import com.condominio.ConvivoApp.user.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository per l'entità Role.
 * JpaRepository fornisce già metodi base come findAll, save, deleteById, etc.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Permette di trovare un ruolo tramite l'enum RoleName.
     * Es: roleRepository.findByName(RoleName.ROLE_ADMIN)
     */
    Optional<Role> findByName(RoleName roleName);
}

