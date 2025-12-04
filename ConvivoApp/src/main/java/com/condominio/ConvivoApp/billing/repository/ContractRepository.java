package com.condominio.ConvivoApp.billing.repository;

import com.condominio.ConvivoApp.billing.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository per la gestione dei contratti.
 * Fornisce metodi CRUD e query personalizzate.
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    /**
     * Trova tutti i contratti firmati da uno specifico utente.
     *
     * @param signedById ID dell'utente che ha firmato
     * @return lista di contratti
     */
    List<Contract> findBySignedById(UUID signedById);

    /**
     * Altre query personalizzate possono essere aggiunte qui.
     */
}
