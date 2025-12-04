package com.condominio.ConvivoApp.billing.controller;

import com.condominio.ConvivoApp.billing.dto.InvoiceDTO;
import com.condominio.ConvivoApp.billing.entity.InvoiceStatus;
import com.condominio.ConvivoApp.billing.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione delle fatture (Invoice).
 */
@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    // ----------------------------------------
    // CREATE
    // ----------------------------------------
    @PostMapping
    public ResponseEntity<InvoiceDTO> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO) {
        // Crea una nuova fattura
        InvoiceDTO created = invoiceService.createInvoice(invoiceDTO);
        return ResponseEntity.ok(created);
    }

    // ----------------------------------------
    // READ
    // ----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        // Recupera una fattura tramite ID
        InvoiceDTO invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        // Recupera tutte le fatture
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByContract(@PathVariable Long contractId) {
        // Recupera tutte le fatture di un contratto specifico
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByContract(contractId);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByStatus(@PathVariable InvoiceStatus status) {
        // Recupera le fatture per stato (PENDING, PAID, OVERDUE)
        List<InvoiceDTO> invoices = invoiceService.getInvoicesByStatus(status);
        return ResponseEntity.ok(invoices);
    }

    // ----------------------------------------
    // UPDATE
    // ----------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @Valid @RequestBody InvoiceDTO invoiceDTO) {
        // Aggiorna una fattura esistente
        InvoiceDTO updated = invoiceService.updateInvoice(id, invoiceDTO);
        return ResponseEntity.ok(updated);
    }

    // ----------------------------------------
    // DELETE
    // ----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        // Elimina una fattura tramite ID
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
