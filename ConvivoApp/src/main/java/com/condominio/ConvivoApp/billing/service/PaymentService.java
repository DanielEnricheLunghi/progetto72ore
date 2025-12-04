package com.condominio.ConvivoApp.billing.service;

import com.condominio.ConvivoApp.billing.dto.PaymentDTO;
import com.condominio.ConvivoApp.billing.entity.PaymentStatus;

import java.util.List;

/**
 * Interfaccia per la gestione dei pagamenti (Payment).
 */
public interface PaymentService {

    /**
     * Crea un nuovo pagamento.
     */
    PaymentDTO createPayment(PaymentDTO paymentDTO);

    /**
     * Aggiorna un pagamento esistente.
     */
    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO);

    /**
     * Recupera un pagamento tramite ID.
     */
    PaymentDTO getPaymentById(Long id);

    /**
     * Recupera tutti i pagamenti.
     */
    List<PaymentDTO> getAllPayments();

    /**
     * Recupera tutti i pagamenti associati a una fattura specifica.
     */
    List<PaymentDTO> getPaymentsByInvoice(Long invoiceId);

    /**
     * Recupera i pagamenti per stato (PENDING, COMPLETED, FAILED).
     */
    List<PaymentDTO> getPaymentsByStatus(PaymentStatus status);

    /**
     * Elimina un pagamento tramite ID.
     */
    void deletePayment(Long id);
}
