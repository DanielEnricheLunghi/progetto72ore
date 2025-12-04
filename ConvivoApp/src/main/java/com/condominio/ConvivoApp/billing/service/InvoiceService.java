package com.condominio.ConvivoApp.billing.service;

import com.condominio.ConvivoApp.billing.dto.InvoiceDTO;
import com.condominio.ConvivoApp.billing.entity.InvoiceStatus;

import java.util.List;

/**
 * Interfaccia per la gestione delle fatture (Invoice).
 */
public interface InvoiceService {

    /**
     * Crea una nuova fattura.
     */
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    /**
     * Aggiorna una fattura esistente.
     */
    InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO);

    /**
     * Recupera una fattura tramite ID.
     */
    InvoiceDTO getInvoiceById(Long id);

    /**
     * Recupera tutte le fatture.
     */
    List<InvoiceDTO> getAllInvoices();

    /**
     * Recupera tutte le fatture di un contratto specifico.
     */
    List<InvoiceDTO> getInvoicesByContract(Long contractId);

    /**
     * Recupera le fatture per stato (PENDING, PAID, OVERDUE).
     */
    List<InvoiceDTO> getInvoicesByStatus(InvoiceStatus status);

    /**
     * Elimina una fattura tramite ID.
     */
    void deleteInvoice(Long id);
}
