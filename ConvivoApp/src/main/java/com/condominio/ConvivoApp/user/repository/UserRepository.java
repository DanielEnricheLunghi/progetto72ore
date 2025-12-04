package com.condominio.ConvivoApp.user.repository;

import com.condominio.ConvivoApp.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository per l'entità User.
 */
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Cerca un utente tramite email (utile per login).
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica se un utente con quella email esiste già.
     */
    boolean existsByEmail(String email);

    /**
     * Cerca un utente tramite username.
     */
    Optional<User> findByUsername(String username);
}
