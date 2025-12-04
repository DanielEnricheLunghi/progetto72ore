package com.condominio.ConvivoApp.billing.controller;

import com.condominio.ConvivoApp.billing.dto.PaymentDTO;
import com.condominio.ConvivoApp.billing.entity.PaymentStatus;
import com.condominio.ConvivoApp.billing.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST per la gestione dei pagamenti (Payment).
 */
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // ----------------------------------------
    // CREATE
    // ----------------------------------------
    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        // Crea un nuovo pagamento
        PaymentDTO created = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(created);
    }

    // ----------------------------------------
    // READ
    // ----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable Long id) {
        // Recupera un pagamento tramite ID
        PaymentDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        // Recupera tutti i pagamenti
        List<PaymentDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByInvoice(@PathVariable Long invoiceId) {
        // Recupera tutti i pagamenti di una fattura specifica
        List<PaymentDTO> payments = paymentService.getPaymentsByInvoice(invoiceId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        // Recupera tutti i pagamenti per stato (PENDING, COMPLETED, FAILED)
        List<PaymentDTO> payments = paymentService.getPaymentsByStatus(status);
        return ResponseEntity.ok(payments);
    }

    // ----------------------------------------
    // UPDATE
    // ----------------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDTO paymentDTO) {
        // Aggiorna un pagamento esistente
        PaymentDTO updated = paymentService.updatePayment(id, paymentDTO);
        return ResponseEntity.ok(updated);
    }

    // ----------------------------------------
    // DELETE
    // ----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        // Elimina un pagamento tramite ID
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
