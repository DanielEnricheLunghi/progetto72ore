package com.condominio.ConvivoApp.billing.repository;

import com.condominio.ConvivoApp.billing.entity.Invoice;
import com.condominio.ConvivoApp.billing.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione delle fatture.
 * Fornisce metodi CRUD e query personalizzate.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    /**
     * Trova tutte le fatture associate a un contratto specifico.
     *
     * @param contractId ID del contratto
     * @return lista di fatture
     */
    List<Invoice> findByContractId(Long contractId);

    /**
     * Trova tutte le fatture con uno stato specifico (PENDING, PAID, OVERDUE).
     *
     * @param status stato della fattura
     * @return lista di fatture
     */
    List<Invoice> findByStatus(InvoiceStatus status);
}
