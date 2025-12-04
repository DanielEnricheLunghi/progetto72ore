package com.condominio.ConvivoApp.billing.service;

import com.condominio.ConvivoApp.billing.dto.InvoiceDTO;
import com.condominio.ConvivoApp.billing.entity.Contract;
import com.condominio.ConvivoApp.billing.entity.Invoice;
import com.condominio.ConvivoApp.billing.entity.InvoiceStatus;
import com.condominio.ConvivoApp.billing.repository.ContractRepository;
import com.condominio.ConvivoApp.billing.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implementazione concreta del service per la gestione delle fatture.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ContractRepository contractRepository;

    /**
     * Crea una nuova fattura.
     */
    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {

        Invoice invoice = new Invoice();

        // Recupero del contratto associato
        Contract contract = contractRepository.findById(invoiceDTO.getContractId())
                .orElseThrow(() -> new NoSuchElementException("Contract not found"));

        invoice.setContract(contract);

        // Set dei campi principali
        invoice.setAmount(invoiceDTO.getAmount());
        invoice.setDueDate(invoiceDTO.getDueDate());
        invoice.setPaidDate(invoiceDTO.getPaidDate());

        // Se non viene passato uno stato, imposta PENDING
        invoice.setStatus(invoiceDTO.getStatus() != null ? invoiceDTO.getStatus() : InvoiceStatus.PENDING);

        invoice.setReference(invoiceDTO.getReference());

        // Salvataggio su DB
        Invoice saved = invoiceRepository.save(invoice);
        return convertToDTO(saved);
    }

    /**
     * Aggiorna una fattura esistente con controlli di stato coerenti.
     */
    @Override
    public InvoiceDTO updateInvoice(Long id, InvoiceDTO dto) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Invoice not found"));

        // Controllo dello stato coerente prima di applicare modifiche
        if (dto.getStatus() != null) {
            validateInvoiceStateChange(invoice, dto.getStatus());
        }

        // Aggiorna i campi solo se presenti
        if (dto.getAmount() != null) invoice.setAmount(dto.getAmount());
        if (dto.getDueDate() != null) invoice.setDueDate(dto.getDueDate());
        if (dto.getPaidDate() != null) invoice.setPaidDate(dto.getPaidDate());
        if (dto.getReference() != null) invoice.setReference(dto.getReference());
        if (dto.getStatus() != null) invoice.setStatus(dto.getStatus());

        Invoice updated = invoiceRepository.save(invoice);
        return convertToDTO(updated);
    }

    /**
     * Trova una fattura tramite ID.
     */
    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new NoSuchElementException("Invoice not found"));
    }

    /**
     * Restituisce tutte le fatture.
     */
    @Override
    public List<InvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Restituisce le fatture associate ad un contratto.
     */
    @Override
    public List<InvoiceDTO> getInvoicesByContract(Long contractId) {
        return invoiceRepository.findByContractId(contractId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Restituisce le fatture filtrate per stato.
     */
    @Override
    public List<InvoiceDTO> getInvoicesByStatus(InvoiceStatus status) {
        return invoiceRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Elimina una fattura tramite ID.
     */
    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    /**
     * Converte una entità Invoice nel relativo DTO.
     */
    private InvoiceDTO convertToDTO(Invoice invoice) {
        return InvoiceDTO.builder()
                .id(invoice.getId())
                .contractId(invoice.getContract() != null ? invoice.getContract().getId() : null)
                .amount(invoice.getAmount())
                .dueDate(invoice.getDueDate())
                .paidDate(invoice.getPaidDate())
                .status(invoice.getStatus())
                .reference(invoice.getReference())
                .createdAt(invoice.getCreatedAt())
                .build();
    }

    /**
     * Verifica che il cambio di stato sia valido e coerente con lo stato corrente.
     * Impedisce stati impossibili (es. modificare una fattura PAID).
     */
    private void validateInvoiceStateChange(Invoice invoice, InvoiceStatus newStatus) {

        InvoiceStatus current = invoice.getStatus();

        // Una fattura già pagata non può più cambiare stato
        if (current == InvoiceStatus.PAID) {
            throw new IllegalStateException("A paid invoice cannot change state.");
        }

        // Da PENDING → solo verso PAID o OVERDUE
        if (current == InvoiceStatus.PENDING &&
                !(newStatus == InvoiceStatus.PAID || newStatus == InvoiceStatus.OVERDUE)) {
            throw new IllegalStateException("Pending invoices can only become PAID or OVERDUE.");
        }

        // Da OVERDUE → solo verso PAID
        if (current == InvoiceStatus.OVERDUE && newStatus != InvoiceStatus.PAID) {
            throw new IllegalStateException("Overdue invoices can only become PAID.");
        }
    }

}
