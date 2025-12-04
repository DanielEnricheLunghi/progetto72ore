package com.condominio.ConvivoApp.billing.repository;

import com.condominio.ConvivoApp.billing.entity.Payment;
import com.condominio.ConvivoApp.billing.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository per la gestione dei pagamenti.
 * Fornisce metodi CRUD e query personalizzate.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Trova tutti i pagamenti associati a una fattura specifica.
     *
     * @param invoiceId ID della fattura
     * @return lista di pagamenti
     */
    List<Payment> findByInvoiceId(Long invoiceId);

    /**
     * Trova tutti i pagamenti con uno stato specifico (PENDING, COMPLETED, FAILED).
     *
     * @param status stato del pagamento
     * @return lista di pagamenti
     */
    List<Payment> findByStatus(PaymentStatus status);
}
